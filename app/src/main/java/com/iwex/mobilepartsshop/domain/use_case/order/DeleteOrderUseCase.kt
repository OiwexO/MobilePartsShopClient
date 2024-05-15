package com.iwex.mobilepartsshop.domain.use_case.order

import com.iwex.mobilepartsshop.domain.repository.order.OrderRepository
import javax.inject.Inject

class DeleteOrderUseCase @Inject constructor(private val orderRepository: OrderRepository) {

    suspend operator fun invoke(id: Long): Result<Unit> {
        return orderRepository.deleteOrder(id)
    }
}