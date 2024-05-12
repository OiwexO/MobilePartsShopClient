package com.iwex.mobilepartsshop.domain.repository.order

import com.iwex.mobilepartsshop.domain.entity.order.Order
import com.iwex.mobilepartsshop.domain.entity.order.OrderRequest

interface OrderRepository {

    suspend fun getOrdersByCustomerId(customerId: Long): Result<List<Order>>

    suspend fun getOrder(id: Long): Result<Order>

    suspend fun createOrder(orderRequest: OrderRequest): Result<Order>

    suspend fun updateOrder(id: Long, orderRequest: OrderRequest): Result<Order>

    suspend fun deleteOrder(id: Long): Result<Unit>
}