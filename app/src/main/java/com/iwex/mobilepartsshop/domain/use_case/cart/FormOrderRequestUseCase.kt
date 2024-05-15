package com.iwex.mobilepartsshop.domain.use_case.cart

import com.iwex.mobilepartsshop.domain.entity.order.OrderRequest
import com.iwex.mobilepartsshop.domain.repository.cart.CartRepository
import javax.inject.Inject

class FormOrderRequestUseCase @Inject constructor(private val cartRepository: CartRepository) {
    suspend operator fun invoke(shippingAddressId: Long): Result<OrderRequest> {
        return cartRepository.formOrderRequest(shippingAddressId)
    }
}
