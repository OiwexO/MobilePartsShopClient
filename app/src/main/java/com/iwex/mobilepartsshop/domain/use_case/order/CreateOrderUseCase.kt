package com.iwex.mobilepartsshop.domain.use_case.order

import com.iwex.mobilepartsshop.domain.entity.order.Order
import com.iwex.mobilepartsshop.domain.entity.order.OrderRequest
import com.iwex.mobilepartsshop.domain.repository.order.OrderRepository
import javax.inject.Inject

class CreateOrderUseCase @Inject constructor(private val orderRepository: OrderRepository) {

    suspend operator fun invoke(orderRequest: OrderRequest): Result<Order> {
        return orderRepository.createOrder(orderRequest)
    }
}