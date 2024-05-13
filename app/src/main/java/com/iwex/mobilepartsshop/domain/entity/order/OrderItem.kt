package com.iwex.mobilepartsshop.domain.entity.order

import com.iwex.mobilepartsshop.domain.entity.part.Part

data class OrderItem(
    val id: Long,
    val orderId: Long,
    val part: Part,
    val quantity: Int,
)
