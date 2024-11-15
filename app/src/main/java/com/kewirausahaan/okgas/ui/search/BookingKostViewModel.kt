package com.kewirausahaan.okgas.ui.search

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

class BookingKostViewModel : ViewModel() {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("order")

    fun saveOrder(name: String, date: String, phone: String, kostName: String, kostLocation: String) {
        val orderId = UUID.randomUUID().toString()
        val orderData = mapOf(
            "name" to name,
            "date" to date,
            "phone" to phone,
            "kost_name" to kostName,
            "kost_location" to kostLocation,
            "price" to 35000,
            "service" to "pengecekan",
            "status" to "mencari",
            "partner_name" to null,
            "partner_phone" to null
        )

        database.child(orderId).setValue(orderData)
    }
}