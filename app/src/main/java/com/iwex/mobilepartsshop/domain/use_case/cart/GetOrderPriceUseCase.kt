package com.iwex.mobilepartsshop.domain.use_case.cart

import com.iwex.mobilepartsshop.domain.repository.cart.CartRepository
import javax.inject.Inject

class GetOrderPriceUseCase @Inject constructor(private val cartRepository: CartRepository) {
    suspend operator fun invoke(): Result<Double> {
        return cartRepository.getOrderPrice()
    }
}