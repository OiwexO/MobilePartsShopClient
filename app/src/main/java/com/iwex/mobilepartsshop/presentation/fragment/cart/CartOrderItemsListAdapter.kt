package com.iwex.mobilepartsshop.presentation.fragment.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.order.OrderItem

class CartOrderItemsListAdapter :
    ListAdapter<OrderItem, CartOrderItemViewHolder>(CartOrderItemDiffCallback()) {

    var isUkrainianLocale = false

    var onPartQuantityChanged: ((OrderItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartOrderItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_order_item, parent, false)
        return CartOrderItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartOrderItemViewHolder, position: Int) {
        val item = currentList[position]
        val part = item.part
        val context = holder.itemView.context
        Glide.with(context)
            .load(part.imageUrl)
            .into(holder.imageViewPartImage)
        holder.textViewName.text = part.name
        holder.textViewPartType.text =
            if (isUkrainianLocale) part.partType.nameUk else part.partType.nameEn
        holder.textViewManufacturerName.text = part.manufacturer.name
        holder.textViewDeviceType.text =
            if (isUkrainianLocale) part.deviceType.nameUk else part.deviceType.nameEn
        holder.textViewPrice.text =
            context.getString(R.string.price_placeholder, part.price.toString())
        holder.counterQuantity.setCount(item.quantity)
        holder.counterQuantity.minValue = 1
        holder.counterQuantity.maxValue = item.part.quantity
        holder.counterQuantity.onCountChanged = { count ->
            onPartQuantityChanged?.let {
                val orderItem = item.copy(quantity = count)
                it.invoke(orderItem)
            }
        }
    }
}