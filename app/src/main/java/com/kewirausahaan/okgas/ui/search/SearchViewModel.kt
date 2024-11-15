package com.kewirausahaan.okgas.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*

class SearchViewModel : ViewModel() {
    private val _kostList = MutableLiveData<List<Kost>>()
    val kostList: LiveData<List<Kost>> get() = _kostList

    init {
        fetchKostData()
    }

    private fun fetchKostData() {
        val database = FirebaseDatabase.getInstance().getReference("kost")
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val kosts = mutableListOf<Kost>()
                snapshot.children.forEach { kostSnapshot ->
                    val kost = kostSnapshot.getValue(Kost::class.java)
                    kost?.let { kosts.add(it) }
                }
                _kostList.value = kosts
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("SearchViewModel", "Database error: ${error.message}")
            }
        })
    }
}
