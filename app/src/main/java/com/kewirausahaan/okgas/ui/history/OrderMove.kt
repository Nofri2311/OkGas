package com.kewirausahaan.okgas.ui.history

data class OrderMove(
    val date: String = "",
    val location_destination: String = "",
    val location_now: String = "",
    val name: String = "",
    val partner_name: String = "",
    val partner_phone: String = "",
    val phone: String = "",
    val price: Int = 0,
    val service: String = "",
    val status: String = ""
)