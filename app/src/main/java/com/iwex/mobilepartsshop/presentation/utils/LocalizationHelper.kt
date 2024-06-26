package com.iwex.mobilepartsshop.presentation.utils

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes
import com.iwex.mobilepartsshop.R
import com.iwex.mobilepartsshop.domain.entity.order.OrderStatus

object LocalizationHelper {

    fun isUkrainianLocale(resources: Resources): Boolean {
        val currentLocale = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            resources.configuration.locales[0]
        } else {
            @Suppress("DEPRECATION")
            resources.configuration.locale
        }
        return currentLocale.language == "uk"
    }

    fun getLocalizedOrderStatus(status: OrderStatus, context: Context): String {
        @StringRes val statusRes = when (status) {
            OrderStatus.PENDING -> R.string.order_status_pending
            OrderStatus.PROCESSING -> R.string.order_status_processing
            OrderStatus.SHIPPING -> R.string.order_status_shipping
            OrderStatus.DELIVERED -> R.string.order_status_delivered
            OrderStatus.COMPLETED -> R.string.order_status_completed
            OrderStatus.CANCELED -> R.string.order_status_canceled
        }
        return context.getString(statusRes)
    }

    fun getLocalizedString(resources: Resources, stringEn: String, stringUk: String): String {
        return if (isUkrainianLocale(resources)) {
            stringUk
        } else {
            stringEn
        }
    }

}