package com.iwex.mobilepartsshop.data.repository.order

import com.iwex.mobilepartsshop.data.remote.MainApiService
import com.iwex.mobilepartsshop.data.remote.dto.mapper.order.OrderMapper
import com.iwex.mobilepartsshop.domain.entity.order.Order
import com.iwex.mobilepartsshop.domain.entity.order.OrderRequest
import com.iwex.mobilepartsshop.domain.repository.order.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: OrderMapper,
) : OrderRepository {

    override suspend fun getOrdersByCustomerId(customerId: Long): Result<List<Order>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOrder(id: Long): Result<Order> {
        TODO("Not yet implemented")
    }

    override suspend fun createOrder(orderRequest: OrderRequest): Result<Order> {
        TODO("Not yet implemented")
    }

    override suspend fun updateOrder(id: Long, orderRequest: OrderRequest): Result<Order> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOrder(id: Long): Result<Unit> {
        TODO("Not yet implemented")
    }
}