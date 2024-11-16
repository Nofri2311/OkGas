package com.kewirausahaan.okgas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kewirausahaan.okgas.databinding.ItemHistoryBinding
import com.kewirausahaan.okgas.ui.history.OrderSearch

class OrderSearchAdapter : ListAdapter<OrderSearch, OrderSearchAdapter.OrderSearchViewHolder>(DiffCallback) {

    class OrderSearchViewHolder(private var binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(orderData: OrderSearch) {
            binding.detailOrderDate.text = orderData.date
            binding.detailPartnerNumber.text = orderData.partner_phone
            binding.detailOrderStatus.text = orderData.status
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderSearchViewHolder {
        return OrderSearchViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderSearchViewHolder, position: Int) {
        val orderData = getItem(position)
        holder.bind(orderData)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<OrderSearch>() {
        override fun areItemsTheSame(oldItem: OrderSearch, newItem: OrderSearch): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: OrderSearch, newItem: OrderSearch): Boolean {
            return oldItem.date == newItem.date
        }
    }
}