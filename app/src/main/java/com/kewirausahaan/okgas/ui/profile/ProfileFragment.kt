package com.kewirausahaan.okgas.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.kewirausahaan.okgas.R
import com.kewirausahaan.okgas.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.profile.observe(viewLifecycleOwner) { profile ->
            binding.detailProfileName.text = profile.name
            binding.detailProfileEmail.text = profile.email

            // Menampilkan gambar profil jika ada
            if (profile.picture == null || profile.picture == "") {
                val storageReference = FirebaseStorage.getInstance().getReference("/picture/default.jpg")
                storageReference.downloadUrl.addOnSuccessListener { uri ->
                    Glide.with(binding.profileImage.context)
                        .load(uri)
                        .into(binding.profileImage)
                }
            } else {
                // Set gambar profil sebagai null jika tidak ada
                val storageReference = FirebaseStorage.getInstance().getReference("/picture/${profile.picture}.jpg")
                storageReference.downloadUrl.addOnSuccessListener { uri ->
                    Glide.with(binding.profileImage.context)
                        .load(uri)
                        .into(binding.profileImage)
                }
            }
        }

        // Logout
        binding.buttonLogout.setOnClickListener {
            viewModel.logout()
            Toast.makeText(requireContext(), "Berhasil Logout", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_login)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}