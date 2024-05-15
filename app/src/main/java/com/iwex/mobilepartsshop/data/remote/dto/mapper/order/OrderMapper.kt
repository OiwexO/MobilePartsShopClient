package com.iwex.mobilepartsshop.data.remote.dto.mapper.order

import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseRequestMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.user.address.AddressMapper
import com.iwex.mobilepartsshop.data.remote.dto.order.OrderRequestDto
import com.iwex.mobilepartsshop.data.remote.dto.order.OrderResponseDto
import com.iwex.mobilepartsshop.domain.entity.order.Order
import com.iwex.mobilepartsshop.domain.entity.order.OrderRequest
import javax.inject.Inject

class OrderMapper @Inject constructor(
    private val orderItemMapper: OrderItemMapper,
    private val addressMapper: AddressMapper,
) : ResponseRequestMapper<Order, OrderRequest, OrderResponseDto, OrderRequestDto>() {

    override fun toEntity(dto: OrderResponseDto): Order {
        val orderItems = orderItemMapper.toEntityList(dto.orderItems)
        val shippingAddress = addressMapper.toEntity(dto.shippingAddress)
        return Order(
            id = dto.id,
            orderItems = orderItems,
            price = dto.price,
            status = dto.status,
            date = dto.date,
            customerId = dto.customerId,
            staffId = dto.staffId,
            shippingAddress = shippingAddress
        )
    }

    override fun toRequestDto(request: OrderRequest): OrderRequestDto {
        val orderItems = orderItemMapper.toRequestDtoList(request.orderItems)
        return OrderRequestDto(
            orderItems = orderItems,
            shippingAddressId = request.shippingAddressId
        )
    }
}