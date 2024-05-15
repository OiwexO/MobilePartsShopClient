package com.iwex.mobilepartsshop.presentation.fragment.parts.details

import androidx.recyclerview.widget.DiffUtil
import com.iwex.mobilepartsshop.domain.entity.review.Review

class ReviewDiffCallback : DiffUtil.ItemCallback<Review>() {

    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}