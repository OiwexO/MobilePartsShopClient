package com.iwex.mobilepartsshop.domain.entity.order

data class OrderRequest(
    val orderItems: List<OrderItem>,
    val shippingAddressId: Long,
)