package com.iwex.mobilepartsshop.presentation.fragment.cart

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.presentation.common.view.VerticalCounterView

class CartOrderItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val imageViewPartImage: ImageView = view.findViewById(R.id.imageViewPartImage)

    val textViewName: TextView = view.findViewById(R.id.textViewName)

    val textViewPartType: TextView = view.findViewById(R.id.textViewPartType)

    val textViewManufacturerName: TextView = view.findViewById(R.id.textViewManufacturerName)

    val textViewDeviceType: TextView = view.findViewById(R.id.textViewDeviceType)

    val textViewPrice: TextView = view.findViewById(R.id.textViewPrice)

    val counterQuantity: VerticalCounterView = view.findViewById(R.id.counterQuantity)
}