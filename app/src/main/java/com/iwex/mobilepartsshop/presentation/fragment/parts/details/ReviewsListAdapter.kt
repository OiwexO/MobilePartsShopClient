package com.iwex.mobilepartsshop.presentation.fragment.parts.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.ListAdapter
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.review.Review
import java.text.SimpleDateFormat
import java.util.Locale

class ReviewsListAdapter : ListAdapter<Review, ReviewViewHolder>(ReviewDiffCallback()) {

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val item = currentList[position]
        val name = "${item.author.firstname} ${item.author.lastname}"
        holder.textViewReviewAuthor.text = name
        @DrawableRes val emojiRes = getEmojiRes(item.rating)
        holder.imageViewReviewEmoji.setImageResource(emojiRes)
        val dateStr = dateFormat.format(item.publicationDate)
        holder.textViewReviewDate.text = dateStr
        holder.textViewReviewIsEdited.visibility = if (item.isEdited) View.VISIBLE else View.GONE
        holder.textViewReviewRating.text =
            holder.itemView.context.getString(R.string.rating_placeholder, item.rating)
        holder.textViewReviewText.text = item.text
    }

    @DrawableRes
    private fun getEmojiRes(rating: Int): Int {
        return when (rating) {
            in 0..4 -> R.drawable.ic_emoji_dissatisfied
            in 5..7 -> R.drawable.ic_emoji_neutral
            in 8..10 -> R.drawable.ic_emoji_satisfied
            else -> R.drawable.ic_emoji_neutral
        }
    }
}