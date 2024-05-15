package com.iwex.mobilepartsshop.domain.use_case.order

import com.iwex.mobilepartsshop.domain.entity.order.Order
import com.iwex.mobilepartsshop.domain.repository.order.OrderRepository
import javax.inject.Inject

class GetOrdersByCustomerIdUseCase @Inject constructor(private val orderRepository: OrderRepository) {

    suspend operator fun invoke(customerId: Long): Result<List<Order>> {
        return orderRepository.getOrdersByCustomerId(customerId)
    }
}