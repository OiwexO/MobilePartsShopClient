package com.iwex.mobilepartsshop.presentation.fragment.cart

import androidx.recyclerview.widget.DiffUtil
import com.iwex.mobilepartsshop.domain.entity.order.OrderItem

class CartOrderItemDiffCallback : DiffUtil.ItemCallback<OrderItem>() {

    override fun areItemsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean {
        return oldItem.part.id == newItem.part.id
    }

    override fun areContentsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean {
        return oldItem == newItem
    }
}