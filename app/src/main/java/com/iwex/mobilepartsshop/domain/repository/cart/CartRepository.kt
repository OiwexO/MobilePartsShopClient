package com.iwex.mobilepartsshop.domain.repository.cart

import com.iwex.mobilepartsshop.domain.entity.order.OrderItem
import com.iwex.mobilepartsshop.domain.entity.order.OrderRequest
import com.iwex.mobilepartsshop.domain.entity.part.Part

interface CartRepository {

    suspend fun getAllOrderItems(): Result<List<OrderItem>>

    suspend fun addPartToCart(part: Part): Result<Unit>

    suspend fun getOrderPrice(): Result<Double>

    suspend fun deletePartFromCart(partId: Long): Result<Unit>

    suspend fun changePartQuantity(partId: Long, quantity: Int): Result<Unit>

    suspend fun formOrderRequest(shippingAddressId: Long): Result<OrderRequest>

    suspend fun emptyCart(): Result<Unit>
}