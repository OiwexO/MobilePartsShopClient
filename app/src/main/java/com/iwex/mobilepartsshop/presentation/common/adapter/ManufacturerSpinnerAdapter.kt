package com.iwex.mobilepartsshop.presentation.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.part.manufacturer.Manufacturer

class ManufacturerSpinnerAdapter(
    context: Context,
    private val manufacturers: List<Manufacturer>
) : ArrayAdapter<Manufacturer>(context, 0, manufacturers) {

    // Define a constant for the "any" option
    private val ANY_MANUFACTURER = Manufacturer(-1, "Any", "")

    // Modified getCount to include the extra option
    override fun getCount(): Int {
        return super.getCount() + 1 // Add 1 for the "any" option
    }

    // Modified getItem to handle the "any" option
    override fun getItem(position: Int): Manufacturer? {
        return if (position == 0) ANY_MANUFACTURER else super.getItem(position - 1)
    }

    // Modified getView and getDropDownView to handle the "any" option
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_item_manufacturer, parent, false)

        val manufacturer = getItem(position) ?: return view

        val imageViewManufacturerLogo = view.findViewById<ImageView>(R.id.imageViewManufacturerLogo)
        val textViewManufacturerName = view.findViewById<TextView>(R.id.textViewManufacturerName)

        if (manufacturer == ANY_MANUFACTURER) {
            imageViewManufacturerLogo.setImageDrawable(null) // Clear the logo
        } else {
            // Load the actual manufacturer data
            Glide.with(view)
                .load(manufacturer.logoUrl)
                .into(imageViewManufacturerLogo)
        }
        textViewManufacturerName.text = manufacturer.name

        return view
    }
}


