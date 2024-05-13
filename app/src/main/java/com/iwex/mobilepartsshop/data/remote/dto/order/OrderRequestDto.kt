package com.iwex.mobilepartsshop.data.remote.dto.order

import com.google.gson.annotations.SerializedName

data class OrderRequestDto(
    @SerializedName("orderItems") val orderItems: List<OrderItemRequestDto>,
    @SerializedName("shippingAddressId") val shippingAddressId: Long
)

