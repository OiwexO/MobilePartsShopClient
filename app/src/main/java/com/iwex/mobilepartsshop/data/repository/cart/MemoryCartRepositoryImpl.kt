package com.iwex.mobilepartsshop.data.repository.cart

import com.iwex.mobilepartsshop.domain.entity.order.OrderItem
import com.iwex.mobilepartsshop.domain.entity.order.OrderItemRequest
import com.iwex.mobilepartsshop.domain.entity.order.OrderRequest
import com.iwex.mobilepartsshop.domain.entity.part.Part
import com.iwex.mobilepartsshop.domain.repository.cart.CartRepository

import kotlin.collections.mutableMapOf

class MemoryCartRepositoryImpl : CartRepository {

    private val cartItems: MutableMap<Long, OrderItem> = mutableMapOf()

    override suspend fun getAllOrderItems(): Result<List<OrderItem>> {
        return Result.success(cartItems.values.toList())
    }

    override suspend fun addPartToCart(part: Part): Result<Unit> {
        if (cartItems.containsKey(part.id)) {
            return Result.failure(Exception("Part is already in the cart"))
        } else {
            cartItems[part.id] = OrderItem(0, 0, part, 1)
            return Result.success(Unit)
        }
    }

    override suspend fun getOrderPrice(): Result<Double> {
        val orderItems = cartItems.values.toList()
        var price = 0.0
        orderItems.forEach {
            price += it.part.price * it.quantity
        }
        return Result.success(price)
    }

    override suspend fun deletePartFromCart(partId: Long): Result<Unit> {
        if (cartItems.containsKey(partId)) {
            cartItems.remove(partId)
            return Result.success(Unit)
        } else {
            return Result.failure(Exception("Part is not in the cart"))
        }
    }

    override suspend fun changePartQuantity(partId: Long, quantity: Int): Result<Unit> {
        if (cartItems.containsKey(partId)) {
            cartItems[partId] = cartItems[partId]!!.copy(quantity = quantity)
            return Result.success(Unit)
        } else {
            return Result.failure(Exception("Part is not in the cart"))
        }
    }

    override suspend fun formOrderRequest(shippingAddressId: Long): Result<OrderRequest> {
        if (cartItems.isEmpty()) {
            return Result.failure(Exception("Cart is empty"))
        } else {
            val orderItems = cartItems.map { OrderItemRequest(it.key, it.value.quantity) }
            val orderRequest = OrderRequest(orderItems, shippingAddressId)
            return Result.success(orderRequest)
        }
    }

    override suspend fun emptyCart(): Result<Unit> {
        cartItems.clear()
        return Result.success(Unit)
    }
}
