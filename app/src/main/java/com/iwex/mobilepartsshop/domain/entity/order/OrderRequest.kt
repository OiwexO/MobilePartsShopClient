package com.iwex.mobilepartsshop.domain.entity.order

data class OrderRequest(
    val orderItems: List<OrderItemRequest>,
    val shippingAddressId: Long,
)