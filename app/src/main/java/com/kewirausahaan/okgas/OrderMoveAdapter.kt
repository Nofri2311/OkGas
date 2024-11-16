package com.kewirausahaan.okgas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kewirausahaan.okgas.databinding.ItemHistoryBinding
import com.kewirausahaan.okgas.ui.history.OrderMove

class OrderMoveAdapter : ListAdapter<OrderMove, OrderMoveAdapter.OrderMoveViewHolder>(DiffCallback) {

    class OrderMoveViewHolder(private var binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(orderData: OrderMove) {
            binding.detailOrderDate.text = orderData.date
            binding.detailPartnerNumber.text = orderData.partner_phone
            binding.detailOrderStatus.text = orderData.status
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderMoveViewHolder {
        return OrderMoveViewHolder(
            ItemHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderMoveViewHolder, position: Int) {
        val orderData = getItem(position)
        holder.bind(orderData)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<OrderMove>() {
        override fun areItemsTheSame(oldItem: OrderMove, newItem: OrderMove): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: OrderMove, newItem: OrderMove): Boolean {
            return oldItem.date == newItem.date
        }
    }
}