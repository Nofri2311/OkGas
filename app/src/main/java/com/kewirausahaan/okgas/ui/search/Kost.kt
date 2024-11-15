package com.kewirausahaan.okgas.ui.search

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Kost(
    val description: String = "",
    val gender: String = "",
    val image: String = "",
    val location: String = "",
    val name: String = "",
    val price: Int = 0
) : Parcelable
