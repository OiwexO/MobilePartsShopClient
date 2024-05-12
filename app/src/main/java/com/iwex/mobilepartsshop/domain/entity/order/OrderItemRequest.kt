package com.iwex.mobilepartsshop.domain.entity.order

data class OrderItemRequest(
    val partId: Long,
    val quantity: Int,
)
