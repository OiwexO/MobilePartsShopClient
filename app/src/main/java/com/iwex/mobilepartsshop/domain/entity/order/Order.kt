package com.iwex.mobilepartsshop.domain.entity.order

import com.iwex.mobilepartsshop.domain.entity.user.Address
import java.util.Date

data class Order(
    val id: Long,
    val orderItems: List<OrderItem>,
    val price: Double,
    val status: OrderStatus,
    val date: Date,
    val customerId: Long,
    val staffId: Long,
    val shippingAddress: Address,
)
