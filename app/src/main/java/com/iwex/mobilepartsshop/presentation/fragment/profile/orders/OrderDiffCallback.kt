package com.iwex.mobilepartsshop.presentation.fragment.profile.orders

import androidx.recyclerview.widget.DiffUtil
import com.iwex.mobilepartsshop.domain.entity.order.Order

class OrderDiffCallback : DiffUtil.ItemCallback<Order>() {

    override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem == newItem
    }
}