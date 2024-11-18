package com.kewirausahaan.okgas.ui.search

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

class BookingKostViewModel : ViewModel() {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("order_search")

    fun saveOrder(name: String, date: String, phone: String, kostName: String, kostImage: String, kostLocation: String, kostGender: String, kostPrice: String ,created: String) {
        val orderId = UUID.randomUUID().toString()
        val orderData = mapOf(
            "name" to name,
            "date" to date,
            "phone" to phone,
            "kost_name" to kostName,
            "kost_image" to kostImage,
            "kost_location" to kostLocation,
            "kost_gender" to kostGender,
            "kost_price" to kostPrice,
            "price" to 35000,
            "service" to "pengecekan",
            "status" to "mencari",
            "created" to created,
            "partner_photo" to "",
            "partner_name" to "",
            "partner_phone" to ""
        )

        database.child(orderId).setValue(orderData)
    }
}