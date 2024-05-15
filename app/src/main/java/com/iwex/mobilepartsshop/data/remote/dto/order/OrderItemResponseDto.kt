package com.iwex.mobilepartsshop.data.remote.dto.order

import com.google.gson.annotations.SerializedName
import com.iwex.mobilepartsshop.data.remote.dto.part.PartResponseDto

data class OrderItemResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("orderId") val orderId: Long,
    @SerializedName("part") val part: PartResponseDto,
    @SerializedName("quantity") val quantity: Int
)