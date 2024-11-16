package com.kewirausahaan.okgas.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kewirausahaan.okgas.OrderMoveAdapter
import com.kewirausahaan.okgas.OrderSearchAdapter
import com.kewirausahaan.okgas.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var moveAdapter: OrderMoveAdapter
    private lateinit var searchAdapter: OrderSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moveAdapter = OrderMoveAdapter()
        searchAdapter = OrderSearchAdapter()

        binding.rvMove.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMove.adapter = moveAdapter

        binding.rvSearch.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSearch.adapter = searchAdapter

        viewModel.moveHistory.observe(viewLifecycleOwner) { moveHistory ->
            moveAdapter.submitList(moveHistory)
        }

        viewModel.searchHistory.observe(viewLifecycleOwner) { searchHistory ->
            searchAdapter.submitList(searchHistory)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}