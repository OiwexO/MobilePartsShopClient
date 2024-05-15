package com.iwex.mobilepartsshop.domain.use_case.cart

import com.iwex.mobilepartsshop.domain.entity.part.Part
import com.iwex.mobilepartsshop.domain.repository.cart.CartRepository
import javax.inject.Inject

class AddPartToCartUseCase @Inject constructor(private val cartRepository: CartRepository) {
    suspend operator fun invoke(part: Part): Result<Unit> {
        return cartRepository.addPartToCart(part)
    }
}