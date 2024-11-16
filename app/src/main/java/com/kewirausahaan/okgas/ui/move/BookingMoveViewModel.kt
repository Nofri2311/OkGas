package com.kewirausahaan.okgas.ui.move

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

class BookingMoveViewModel : ViewModel() {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("order_move")

    fun saveOrder(name: String, locationNow: String, locationDestination: String, date: String, phone: String) {
        val orderId = UUID.randomUUID().toString()
        val orderData = mapOf(
            "name" to name,
            "location_now" to locationNow,
            "location_destination" to locationDestination,
            "date" to date,
            "phone" to phone,
            "price" to 70000,
            "service" to "pindahan",
            "status" to "mencari",
            "partner_name" to "",
            "partner_phone" to ""
        )

        database.child(orderId).setValue(orderData)
    }
}