package com.kewirausahaan.okgas.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailBookingKostViewModel : ViewModel() {

    private val _created = MutableLiveData<String>()
    val created: LiveData<String> = _created

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    private val _kostImage = MutableLiveData<String>()
    val kostImage: LiveData<String> = _kostImage

    private val _kostLocation = MutableLiveData<String>()
    val kostLocation: LiveData<String> = _kostLocation

    private val _kostName = MutableLiveData<String>()
    val kostName: LiveData<String> = _kostName

    private val _kostPrice = MutableLiveData<String>()
    val kostPrice: LiveData<String> = _kostPrice

    private val _kostGender = MutableLiveData<String>()
    val kostGender: LiveData<String> = _kostGender

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _partnerName = MutableLiveData<String>()
    val partnerName: LiveData<String> = _partnerName

    private val _partnerPhone = MutableLiveData<String>()
    val partnerPhone: LiveData<String> = _partnerPhone

    private val _partnerPhoto = MutableLiveData<String>()
    val partnerPhoto: LiveData<String> = _partnerPhoto

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    private val _price = MutableLiveData<Int>()
    val price: LiveData<Int> = _price

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun setData(
        created: String?,
        date: String?,
        id: String?,
        kostImage: String?,
        kostLocation: String?,
        kostName: String?,
        kostPrice: String?,
        kostGender : String?,
        name: String?,
        partnerName: String?,
        partnerPhone: String?,
        partnerPhoto: String?,
        price: Int?,
        status: String?
    ) {
        _created.value = created!!
        _date.value = date!!
        _id.value = id!!
        _kostImage.value = kostImage!!
        _kostLocation.value = kostLocation!!
        _kostName.value = kostName!!
        _kostPrice.value = kostPrice!!
        _kostGender.value = kostGender!!
        _name.value = name!!
        _partnerName.value = partnerName!!
        _partnerPhone.value = partnerPhone!!
        _partnerPhoto.value = partnerPhoto!!
        _price.value = price!!
        _status.value = status!!
    }
}