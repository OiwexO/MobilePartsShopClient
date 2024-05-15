package com.iwex.mobilepartsshop.presentation.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.part.device_type.DeviceType

class DeviceTypeSpinnerAdapter(
    context: Context,
    deviceTypes: List<DeviceType>,
    private val isUkrainianLocale: Boolean,
    showAnyOption: Boolean = false
) : CustomArrayAdapter<DeviceType>(context, deviceTypes, showAnyOption) {

    override fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val deviceType = getItem(position)
        val text1 = view.findViewById<CheckedTextView>(android.R.id.text1)
        if (deviceType == null) {
            text1.text = context.getString(R.string.any)
        } else {
            text1.text = if (isUkrainianLocale) deviceType.nameUk else deviceType.nameEn
        }
        return view
    }
}