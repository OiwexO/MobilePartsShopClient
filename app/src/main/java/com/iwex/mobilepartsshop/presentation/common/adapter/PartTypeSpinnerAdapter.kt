package com.iwex.mobilepartsshop.presentation.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.part.part_type.PartType

class PartTypeSpinnerAdapter(
    context: Context,
    partTypes: List<PartType>,
    private val isUkrainianLocale: Boolean,
    showAnyOption: Boolean = false
) : CustomArrayAdapter<PartType>(context, partTypes, showAnyOption) {

    override fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)
        val partType = getItem(position)
        val text1 = view.findViewById<CheckedTextView>(android.R.id.text1)
        if (partType == null) {
            text1.text = context.getString(R.string.any)
        } else {
            text1.text = if (isUkrainianLocale) partType.nameUk else partType.nameEn
        }
        return view
    }
}