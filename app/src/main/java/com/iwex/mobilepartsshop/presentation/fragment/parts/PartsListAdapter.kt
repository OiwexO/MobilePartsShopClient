package com.iwex.mobilepartsshop.presentation.fragment.parts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.part.Part

class PartsListAdapter(
    private val isUkrainianLocale: Boolean = false
) : ListAdapter<Part, PartViewHolder>(PartDiffCallback()) {

    var onPartClickListener: ((Part) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_part, parent, false)
        return PartViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartViewHolder, position: Int) {
        val item = currentList[position]
        Glide.with(holder.itemView)
            .load(item.imageUrl)
            .into(holder.imageViewPartImage)
        holder.textViewName.text = item.name
        holder.textViewSpecifications.text = item.specifications
        holder.textViewPartType.text =
            if (isUkrainianLocale) item.partType.nameUk else item.partType.nameEn
        holder.textViewManufacturerName.text = item.manufacturer.name
        holder.textViewDeviceType.text =
            if (isUkrainianLocale) item.deviceType.nameUk else item.deviceType.nameEn
        holder.textViewPrice.text =
            holder.itemView.context.getString(R.string.price_placeholder, item.price.toString())
        holder.itemView.setOnClickListener {
            onPartClickListener?.invoke(item)
        }
    }
}