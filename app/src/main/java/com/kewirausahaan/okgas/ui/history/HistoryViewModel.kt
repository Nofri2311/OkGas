package com.kewirausahaan.okgas.ui.history

import androidx.fragment.app.add
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HistoryViewModel : ViewModel() {

    private val _moveHistory = MutableLiveData<List<OrderMove>>()
    val moveHistory: LiveData<List<OrderMove>> = _moveHistory

    private val _searchHistory = MutableLiveData<List<OrderSearch>>()
    val searchHistory: LiveData<List<OrderSearch>> = _searchHistory

    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    init {
        fetchMoveHistory()
        fetchSearchHistory()
    }

    private fun fetchMoveHistory() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("order_move")
        databaseReference.orderByChild("user_id").equalTo(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val moveList = mutableListOf<OrderMove>()
                for (orderSnapshot in snapshot.children) {
                    val orderData = orderSnapshot.getValue(OrderMove::class.java)
                    orderData?.let { moveList.add(it) }
                }
                _moveHistory.value = moveList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    private fun fetchSearchHistory() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("order_search")
        databaseReference.orderByChild("user_id").equalTo(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val searchList = mutableListOf<OrderSearch>()
                for (orderSnapshot in snapshot.children) {
                    val orderData = orderSnapshot.getValue(OrderSearch::class.java)
                    orderData?.let { searchList.add(it) }
                }
                _searchHistory.value = searchList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}