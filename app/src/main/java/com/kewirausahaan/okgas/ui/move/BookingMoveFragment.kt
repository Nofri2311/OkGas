package com.kewirausahaan.okgas.ui.move

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ServerValue
import com.kewirausahaan.okgas.R
import com.kewirausahaan.okgas.databinding.FragmentBookingMoveBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class BookingMoveFragment : Fragment() {

    private var _binding: FragmentBookingMoveBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookingMoveViewModel by viewModels()
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingMoveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the date and time picker for inputMoveDate
        binding.inputMoveDate.setOnClickListener {
            showDateTimePicker()
        }

        binding.moveBook.setOnClickListener {
            val name = binding.inputMoveName.text.toString()
            val locationNow = binding.inputMoveLocationNow.text.toString()
            val locationDestination = binding.inputMoveLocationDestination.text.toString()
            val date = binding.inputMoveDate.text.toString()
            val phone = binding.inputMoveNumber.text.toString()

            val createdTimestamp = Date().time
            val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
            val created = formatter.format(createdTimestamp)

            if (name.isNotBlank() && locationNow.isNotBlank() && locationDestination.isNotBlank() &&
                date.isNotBlank() && phone.isNotBlank()) {
                val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
                viewModel.saveOrder(name, locationNow, locationDestination, date, phone, created, userId)
                Toast.makeText(requireContext(), "Sukses Membuat Pesanan", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_navigation_booking_move_to_navigation_history)
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDateTimePicker() {
        // Date picker
        val datePicker = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                showTimePicker()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun showTimePicker() {
        // Time picker
        val timePicker = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)

                // Format date and time
                val dateTimeFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
                binding.inputMoveDate.setText(dateTimeFormat.format(calendar.time))
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePicker.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}