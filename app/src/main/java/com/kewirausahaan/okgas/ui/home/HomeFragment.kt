package com.kewirausahaan.okgas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kewirausahaan.okgas.R
import com.kewirausahaan.okgas.databinding.FragmentHomeBinding
import com.kewirausahaan.okgas.ServiceAdapter
import com.kewirausahaan.okgas.ServiceItem

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerCarousel = binding.recyclerCarousel // Gunakan binding untuk mengakses RecyclerView

        recyclerCarousel.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val serviceList = listOf(
            ServiceItem(R.drawable.pencarian, "Pencarian Kos"),
            ServiceItem(R.drawable.pindahan, "Pindahan Barang")
        )

        recyclerCarousel.adapter = ServiceAdapter(serviceList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}