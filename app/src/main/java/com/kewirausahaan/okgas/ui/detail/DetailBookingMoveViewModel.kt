package com.kewirausahaan.okgas.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailBookingMoveViewModel : ViewModel() {

    private val _created = MutableLiveData<String>()
    val created: LiveData<String> = _created

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _locationDestination = MutableLiveData<String>()
    val locationDestination: LiveData<String> = _locationDestination

    private val _locationNow = MutableLiveData<String>()
    val locationNow: LiveData<String> = _locationNow

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _partnerName = MutableLiveData<String>()
    val partnerName: LiveData<String> = _partnerName

    private val _partnerPhone = MutableLiveData<String>()
    val partnerPhone: LiveData<String> = _partnerPhone

    private val _partnerPhoto = MutableLiveData<String>()
    val partnerPhoto: LiveData<String> = _partnerPhoto

    private val _price = MutableLiveData<Int>()
    val price: LiveData<Int> = _price

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun setData(
        created: String?,
        date: String?,
        locationDestination: String?,
        locationNow: String?,
        phone: String?,
        name: String?,
        partnerName: String?,
        partnerPhone: String?,
        partnerPhoto: String?,
        price: Int?,
        status: String?
    ) {
        _created.value = created!!
        _date.value = date!!
        _locationDestination.value = locationDestination!!
        _locationNow.value = locationNow!!
        _phone.value = phone!!
        _name.value = name!!
        _partnerName.value = partnerName!!
        _partnerPhone.value = partnerPhone!!
        _partnerPhoto.value = partnerPhoto!!
        _price.value = price!!
        _status.value = status!!
    }
}