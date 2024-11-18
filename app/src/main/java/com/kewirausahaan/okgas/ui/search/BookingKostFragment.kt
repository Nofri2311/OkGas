package com.kewirausahaan.okgas.ui.search

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.kewirausahaan.okgas.R
import com.kewirausahaan.okgas.databinding.FragmentBookingKostBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.text.format
import kotlin.text.get
import kotlin.text.isNotBlank
import kotlin.text.set

class BookingKostFragment : Fragment() {

    private var _binding: FragmentBookingKostBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookingKostViewModel by viewModels()
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingKostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kostImage = arguments?.getString("kostImage")
        val kostName = arguments?.getString("kostName")
        val kostLocation = arguments?.getString("kostLocation")
        val kostGender = arguments?.getString("kostGender")
        val kostPrice = arguments?.getString("kostPrice")

        val storageReference = FirebaseStorage.getInstance().getReference("/kost/${kostImage}.png")
        storageReference.downloadUrl.addOnSuccessListener { uri ->
            Glide.with(binding.bookingKostImage.context)
                .load(uri)
                .into(binding.bookingKostImage)
        }

        // Tampilkan data di UI
//        Glide.with(this).load(kostImage).into(binding.bookingKostImage)
        binding.disableDetailName.setText(kostName)
        binding.disableDetailLocation.setText(kostLocation)

        // Set up the date and time picker for inputMoveDate
        binding.inputBookingDate.setOnClickListener {
            showDateTimePicker()
        }

        binding.buttonBook.setOnClickListener {
            val name = binding.inputBookingName.text.toString()
            val date = binding.inputBookingDate.text.toString()
            val phone = binding.inputBookingNumber.text.toString()

            val createdTimestamp = Date().time
            val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
            val created = formatter.format(createdTimestamp)

            if (name.isNotBlank() && date.isNotBlank() && phone.isNotBlank()) {
                viewModel.saveOrder(name, date, phone, kostName.toString(), kostImage.toString() ,kostLocation.toString(), kostGender.toString(), kostPrice.toString(), created)
                Toast.makeText(requireContext(), "Booking successful!", Toast.LENGTH_SHORT).show()
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
                val dateTimeFormat = SimpleDateFormat("dd-MM-yyyy HH:mm",
                    Locale.getDefault())
                binding.inputBookingDate.setText(dateTimeFormat.format(calendar.time))
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