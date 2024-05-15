package com.iwex.mobilepartsshop.presentation.fragment.parts.details

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iwex.mobilepartsshop.R

class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val textViewReviewAuthor: TextView = view.findViewById(R.id.textViewReviewAuthor)

    val imageViewReviewEmoji: ImageView = view.findViewById(R.id.imageViewReviewEmoji)

    val textViewReviewDate: TextView = view.findViewById(R.id.textViewReviewDate)

    val textViewReviewIsEdited: TextView = view.findViewById(R.id.textViewReviewIsEdited)

    val textViewReviewRating: TextView = view.findViewById(R.id.textViewReviewRating)

    val textViewReviewText: TextView = view.findViewById(R.id.textViewReviewText)
}