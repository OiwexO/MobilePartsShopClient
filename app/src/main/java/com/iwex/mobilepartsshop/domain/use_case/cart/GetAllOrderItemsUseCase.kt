package com.iwex.mobilepartsshop.domain.use_case.cart

import com.iwex.mobilepartsshop.domain.entity.order.OrderItem
import com.iwex.mobilepartsshop.domain.repository.cart.CartRepository
import javax.inject.Inject

class GetAllOrderItemsUseCase @Inject constructor(private val cartRepository: CartRepository) {
    suspend operator fun invoke(): Result<List<OrderItem>> {
        return cartRepository.getAllOrderItems()
    }
}