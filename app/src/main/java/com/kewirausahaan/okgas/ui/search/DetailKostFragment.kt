package com.kewirausahaan.okgas.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.kewirausahaan.okgas.R
import com.kewirausahaan.okgas.databinding.FragmentDetailKostBinding  // Ganti dengan nama package Anda

class DetailKostFragment : Fragment() {

    private lateinit var binding: FragmentDetailKostBinding
    private val viewModel: DetailKostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailKostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengambil data dari arguments
        val kost = arguments?.getParcelable<Kost>("kost")
        kost?.let {
            viewModel.setKostData(it)
        }

        // Observasi data kost dari ViewModel
        viewModel.kost.observe(viewLifecycleOwner) { kost ->
            binding.detailName.text = kost.name
            binding.detailGender.text = kost.gender
            binding.detailLocation.text = kost.location
            binding.detailDescription.text = kost.description
            val formattedPrice = String.format("%,d", kost.price)
            binding.detailPrice.text = "Rp $formattedPrice / bulan"

            // Menampilkan gambar dengan Glide
            val storageReference = FirebaseStorage.getInstance().getReference("/kost/${kost.image}.png")
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(binding.detailImage.context)
                    .load(uri)
                    .into(binding.detailImage)

                binding.buttonCheck.setOnClickListener{
                    val bundle = bundleOf(
                        "kostImage" to uri.toString(),
                        "kostName" to kost.name,
                        "kostLocation" to kost.location
                    )
                    findNavController().navigate(R.id.action_navigation_detail_kost_to_navigation_booking_kost, bundle)
                }
            }
        }


    }
}
