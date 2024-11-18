package com.kewirausahaan.okgas.ui.detail

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.kewirausahaan.okgas.R
import com.kewirausahaan.okgas.databinding.FragmentDetailBookingMoveBinding

class DetailBookingMoveFragment : Fragment() {

    private var _binding: FragmentDetailBookingMoveBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailBookingMoveViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBookingMoveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailBookingMoveViewModel::class.java)

        // Mengambil data dari Bundle
        val created = arguments?.getString("created")
        val date = arguments?.getString("date")
        val id = arguments?.getString("id")
        val locationDestination = arguments?.getString("location_destination")
        val locationNow = arguments?.getString("location_now")
        val phone = arguments?.getString("phone")
        val name = arguments?.getString("name")
        val partnerName = arguments?.getString("partner_name")
        val partnerPhone = arguments?.getString("partner_phone")
        val partnerPhoto = arguments?.getString("partner_photo")
        val price = arguments?.getInt("price")
        val status = arguments?.getString("status")

        // Mengirim data ke ViewModel
        viewModel.setData(created, date, id, locationDestination, locationNow, phone, name, partnerName, partnerPhone, partnerPhoto,
            price, status)

        // Menampilkan data di UI
        binding.detailOrderCreated.text = created
        binding.detailOrderDate.text = date
        binding.detailLocationEnd.text = locationDestination
        binding.detailLocationStart.text = locationNow
        binding.detailNumber.text = phone
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