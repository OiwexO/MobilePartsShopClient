package com.iwex.mobilepartsshop.presentation.fragment.parts

import androidx.recyclerview.widget.DiffUtil
import com.iwex.mobilepartsshop.domain.entity.part.Part

class PartDiffCallback : DiffUtil.ItemCallback<Part>() {

    override fun areItemsTheSame(oldItem: Part, newItem: Part): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Part, newItem: Part): Boolean {
        return oldItem == newItem
    }
}