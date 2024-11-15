package com.kewirausahaan.okgas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.kewirausahaan.okgas.databinding.ItemSearchBinding
import com.kewirausahaan.okgas.ui.search.Kost

class SearchAdapter(private val onItemClick: (Kost) -> Unit) : ListAdapter<Kost, SearchAdapter.KostViewHolder>(KostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        val kost = getItem(position)
        holder.bind(kost)
    }

    inner class KostViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(kost: Kost) {
            binding.searchGender.text = kost.gender
            binding.searchText.text = kost.name
            binding.searchLocation.text = kost.location
            val formattedPrice = String.format("%,d", kost.price)
            binding.searchPrice.text = "Rp $formattedPrice / bulan"

            // Load image from Firebase Storage
            val storageReference = FirebaseStorage.getInstance().getReference("/kost/${kost.image}.png")
            storageReference.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(binding.searchImage.context)
                    .load(uri)
                    .into(binding.searchImage)
            }

            // Handle item click
            binding.root.setOnClickListener {
                onItemClick(kost)
            }
        }
    }
}


class KostDiffCallback : DiffUtil.ItemCallback<Kost>() {
    override fun areItemsTheSame(oldItem: Kost, newItem: Kost): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Kost, newItem: Kost): Boolean {
        return oldItem == newItem
    }
}

