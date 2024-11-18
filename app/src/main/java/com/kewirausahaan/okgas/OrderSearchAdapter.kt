package com.kewirausahaan.okgas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kewirausahaan.okgas.databinding.ItemHistoryBinding
import com.kewirausahaan.okgas.ui.history.OrderSearch

class OrderSearchAdapter : ListAdapter<OrderSearch, OrderSearchAdapter.OrderSearchViewHolder>(DiffCallback) {

    class OrderSearchViewHolder(private var binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(orderData: OrderSearch) {
            binding.detailOrderDate.text = orderData.created
            binding.detailPartnerNumber.text = orderData.partner_phone
            binding.detailOrderStatus.text = orderData.status

            binding.detailButton.setOnClickListener { view ->
                val bundle = Bundle()
                bundle.putString("created", orderData.created)
                bundle.putString("date", orderData.date)
                bundle.putString("kost_image", orderData.kost_image)
                bundle.putString("kost_location", orderData.kost_location)
                bundle.putString("kost_name", orderData.kost_name)
                bundle.putString("kost_price", orderData.kost_price)
                bundle.putString("kost_gender", orderData.kost_gender)
                bundle.putString("name", orderData.name)
                bundle.putString("partner_name", orderData.partner_name)
                bundle.putString("partner_phone", orderData.partner_phone)
                bundle.putString("partner_photo", orderData.partner_photo)
                bundle.putInt("price", orderData.price)
                bundle.putString("status", orderData.status)
                view.findNavController().navigate(R.id.action_navigation_history_to_navigation_detail_order_kost, bundle)
            }
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