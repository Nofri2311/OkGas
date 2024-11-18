package com.kewirausahaan.okgas.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.kewirausahaan.okgas.databinding.FragmentDetailBookingKostBinding

class DetailBookingKostFragment : Fragment() {

    private var _binding: FragmentDetailBookingKostBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailBookingKostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBookingKostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailBookingKostViewModel::class.java)

        // Mengambil data dari Bundle
        val created = arguments?.getString("created")
        val date = arguments?.getString("date")
        val id = arguments?.getString("id")
        val kostImage = arguments?.getString("kost_image")
        val kostLocation = arguments?.getString("kost_location")
        val kostName = arguments?.getString("kost_name")
        val kostPrice = arguments?.getString("kost_price")
        val kostGender = arguments?.getString("kost_gender")
        val name = arguments?.getString("name")
        val partnerName = arguments?.getString("partner_name")
        val partnerPhone = arguments?.getString("partner_phone")
        val partnerPhoto = arguments?.getString("partner_photo")
        val price = arguments?.getInt("price")
        val status = arguments?.getString("status")

        // Mengirim data ke ViewModel
        viewModel.setData(created, date, id, kostImage, kostLocation, kostName, kostPrice, kostGender, name, partnerName, partnerPhone, partnerPhoto,
            price, status)

        // Menampilkan data di UI
        binding.detailOrderCreated.text = created
        binding.detailOrderDate.text = date
        val storageReference = FirebaseStorage.getInstance().getReference("/kost/${kostImage}.png")
        storageReference.downloadUrl.addOnSuccessListener { uri ->
            Glide.with(binding.detailBookingKostImage.context)
                .load(uri)
                .into(binding.detailBookingKostImage)
        }
        binding.detailBookingKostLocation.text = kostLocation
        binding.detailBookingKostName.text = kostName
        binding.detailBookingKostPrice.text = "Rp $kostPrice / bulan"
        binding.detailBookingKostGender.text = kostGender
        binding.detailOrderName.text = name
        if ((partnerName == null || partnerName == "") && (partnerPhone == null || partnerPhone == "") && (partnerPhoto == null || partnerPhoto == "")) {
            binding.detailPartnerName.text = "Belum ada mitra"
            binding.detailPartnerNumber.text = "Belum ada mitra"
            val storageReference = FirebaseStorage.getInstance().getReference("/picture/default.jpg")
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(binding.detailPartnerPhoto.context)
                    .load(uri)
                    .into(binding.detailPartnerPhoto)
            }
        } else {
            binding.detailPartnerName.text = partnerName
            binding.detailPartnerNumber.text = partnerPhone
            val storageReference = FirebaseStorage.getInstance().getReference("/picture/${partnerPhoto}.jpg")
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(binding.detailPartnerPhoto.context)
                    .load(uri)
                    .into(binding.detailPartnerPhoto)
            }
        }
        val formattedPrice = String.format("%,d", price)
        binding.detailOrderPrice.text = "Rp $formattedPrice"
        binding.detailOrderStatus.text = status

        if(status == "Mencari Mitra") {
            binding.orderCancel.visibility = View.VISIBLE
            binding.orderCancel.setOnClickListener {
                val databaseReference = FirebaseDatabase.getInstance().getReference("order_move")
                databaseReference.child(id.toString()).child("status").setValue("Pesanan Dibatalkan")
                Toast.makeText(context, "Pesanan dibatalkan", Toast.LENGTH_SHORT).show()
            }
        } else {
            binding.orderCancel.visibility = View.GONE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}