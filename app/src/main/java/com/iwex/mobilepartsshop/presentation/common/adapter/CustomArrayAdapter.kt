package com.iwex.mobilepartsshop.presentation.common.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

/**
 * ArrayAdapter implementation that provides additional functionality for handling
 * lists with an optional "any" option.
 *
 * @param context The context in which the adapter is being used.
 * @param values The list of items to be displayed in the adapter.
 * @param showAnyOption A boolean flag indicating whether to show an "Any" as the first
 * option in the list.
 */
abstract class CustomArrayAdapter<T>(
    context: Context,
    private val values: List<T>,
    private val showAnyOption: Boolean = false
) :
    ArrayAdapter<T>(context, 0, values) {

    override fun getCount(): Int {
        return if (showAnyOption) super.getCount() + 1 else super.getCount()
    }

    override fun getItem(position: Int): T? {
        return if (showAnyOption) {
            if (position == 0) null else super.getItem(position - 1)
        } else {
            super.getItem(position)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    /**
     * Abstract method to be implemented by subclasses to create custom views for items in the adapter.
     * @param position The position of the item in the adapter.
     * @param convertView The recycled view to populate.
     * @param parent The parent view group.
     * @return The custom view for the item at the specified position.
     */
    abstract fun createView(position: Int, convertView: View?, parent: ViewGroup): View

    /**
     * Gets the position of a specific item in the adapter.
     * @param value The item whose position is to be retrieved.
     * @return The position of the item in the adapter or -1 if the value is not present.
     */
    fun positionOf(value: T): Int {
        val realIndex = values.indexOf(value)
        if (realIndex == -1 || !showAnyOption) return realIndex
        return realIndex + 1
    }
}
