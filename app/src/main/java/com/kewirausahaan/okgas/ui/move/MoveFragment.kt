package com.kewirausahaan.okgas.ui.move

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kewirausahaan.okgas.R
import com.kewirausahaan.okgas.databinding.FragmentMoveBinding

class MoveFragment : Fragment() {

    private var _binding: FragmentMoveBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBook.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_move_to_navigation_booking_move)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}