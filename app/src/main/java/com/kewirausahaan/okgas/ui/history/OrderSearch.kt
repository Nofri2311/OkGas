package com.kewirausahaan.okgas.ui.history

data class OrderSearch(
    val date: String = "",
    val kost_location: String = "",
    val kost_name: String = "",
    val name: String = "",
    val partner_name: String = "",
    val partner_phone: String = "",
    val phone: String = "",
    val price: Int = 0,
    val service: String = "",
    val status: String = ""
)