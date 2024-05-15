package com.iwex.mobilepartsshop.domain.use_case.cart

import com.iwex.mobilepartsshop.domain.repository.cart.CartRepository
import javax.inject.Inject

class EmptyCartUseCase @Inject constructor(private val cartRepository: CartRepository) {
    suspend operator fun invoke(): Result<Unit> {
        return cartRepository.emptyCart()
    }
}