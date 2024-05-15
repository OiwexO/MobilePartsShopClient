package com.iwex.mobilepartsshop.data.remote.dto.order

import com.google.gson.annotations.SerializedName

data class OrderItemRequestDto(
    @SerializedName("partId") val partId: Long,
    @SerializedName("quantity") val quantity: Int
)

