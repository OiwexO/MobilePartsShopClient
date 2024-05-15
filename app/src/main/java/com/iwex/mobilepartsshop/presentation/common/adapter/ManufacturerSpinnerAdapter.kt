package com.iwex.mobilepartsshop.presentation.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.part.manufacturer.Manufacturer

class ManufacturerSpinnerAdapter(
    context: Context,
    manufacturers: List<Manufacturer>,
    showAnyOption: Boolean = false
) : CustomArrayAdapter<Manufacturer>(context, manufacturers, showAnyOption) {

    override fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_item_manufacturer, parent, false)
        val manufacturer = getItem(position)
        val imageViewManufacturerLogo = view.findViewById<ImageView>(R.id.imageViewManufacturerLogo)
        val textViewManufacturerName = view.findViewById<TextView>(R.id.textViewManufacturerName)
        if (manufacturer == null) {
            imageViewManufacturerLogo.setImageDrawable(null)
            textViewManufacturerName.text = context.getString(R.string.any)
        } else {
            Glide.with(view)
                .load(manufacturer.logoUrl)
                .into(imageViewManufacturerLogo)
            textViewManufacturerName.text = manufacturer.name
        }
        return view
    }
}


