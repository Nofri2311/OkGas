package com.kewirausahaan.okgas.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kewirausahaan.okgas.R
import com.kewirausahaan.okgas.SearchAdapter
import com.kewirausahaan.okgas.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        setupRecyclerView()
        observeData()
        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = SearchAdapter { kost ->
            // Mengirim data kost ke DetailKostFragment menggunakan Bundle
            val bundle = Bundle().apply {
                putParcelable("kost", kost)
            }
            findNavController().navigate(R.id.action_navigation_search_to_navigation_detail_kost, bundle)
        }
        binding.rvSearch.layoutManager = LinearLayoutManager(context)
        binding.rvSearch.adapter = adapter
    }

    private fun observeData() {
        viewModel.kostList.observe(viewLifecycleOwner) { kostList ->
            adapter.submitList(kostList)
        }
    }
}
