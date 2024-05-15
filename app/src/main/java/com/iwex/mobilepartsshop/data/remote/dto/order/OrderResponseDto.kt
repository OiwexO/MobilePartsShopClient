package com.iwex.mobilepartsshop.data.remote.dto.order

import com.google.gson.annotations.SerializedName
import com.iwex.mobilepartsshop.data.remote.dto.user.address.AddressResponseDto
import com.iwex.mobilepartsshop.domain.entity.order.OrderStatus
import java.util.Date

data class OrderResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("orderItems") val orderItems: List<OrderItemResponseDto>,
    @SerializedName("price") val price: Double,
    @SerializedName("status") val status: OrderStatus,
    @SerializedName("date") val date: Date,
    @SerializedName("customerId") val customerId: Long,
    @SerializedName("staffId") val staffId: Long,
    @SerializedName("shippingAddress") val shippingAddress: AddressResponseDto
)