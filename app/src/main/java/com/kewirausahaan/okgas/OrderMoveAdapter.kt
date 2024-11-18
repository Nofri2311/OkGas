package com.kewirausahaan.okgas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kewirausahaan.okgas.databinding.ItemHistoryBinding
import com.kewirausahaan.okgas.ui.history.OrderMove

class OrderMoveAdapter : ListAdapter<OrderMove, OrderMoveAdapter.OrderMoveViewHolder>(DiffCallback) {

    class OrderMoveViewHolder(private var binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(orderData: OrderMove) {
            binding.detailOrderDate.text = orderData.created
            binding.detailPartnerNumber.text = orderData.partner_phone
            binding.detailOrderStatus.text = orderData.status

            binding.detailButton.setOnClickListener { view ->
                val bundle = Bundle()
                bundle.putString("created", orderData.created)
                bundle.putString("date", orderData.date)
                bundle.putString("location_destination", orderData.location_destination)
                bundle.putString("location_now", orderData.location_now)
                bundle.putString("phone", orderData.phone)
                bundle.putString("name", orderData.name)
                bundle.putString("partner_name", orderData.partner_name)
                bundle.putString("partner_phone", orderData.partner_phone)
                bundle.putString("partner_photo", orderData.partner_photo)
                bundle.putInt("price", orderData.price)
                bundle.putString("status", orderData.status)
                view.findNavController().navigate(R.id.action_navigation_history_to_navigation_detail_order_move, bundle)
            }
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