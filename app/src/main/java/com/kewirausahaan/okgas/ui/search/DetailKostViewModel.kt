package com.kewirausahaan.okgas.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailKostViewModel : ViewModel() {

    private val _kost = MutableLiveData<Kost>()
    val kost: LiveData<Kost> get() = _kost

    fun setKostData(kost: Kost) {
        _kost.value = kost
    }
}
