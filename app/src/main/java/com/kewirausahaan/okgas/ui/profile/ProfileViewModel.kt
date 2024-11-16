package com.kewirausahaan.okgas.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileViewModel : ViewModel() {

    private val _profile = MutableLiveData<Profile>()
    val profile: LiveData<Profile> = _profile

    init {
        fetchProfileData()
    }

    private fun fetchProfileData() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users").child(it)
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val profileData = snapshot.getValue(Profile::class.java)
                    _profile.value = profileData!!
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}