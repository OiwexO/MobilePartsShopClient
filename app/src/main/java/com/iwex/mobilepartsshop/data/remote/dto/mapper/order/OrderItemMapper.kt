package com.iwex.mobilepartsshop.data.remote.dto.mapper.order

import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.PartMapper
import com.iwex.mobilepartsshop.data.remote.dto.order.OrderItemResponseDto
import com.iwex.mobilepartsshop.domain.entity.order.OrderItem
import javax.inject.Inject

class OrderItemMapper @Inject constructor(
    private val partMapper: PartMapper
) : ResponseMapper<OrderItem, OrderItemResponseDto>() {

    override fun toEntity(dto: OrderItemResponseDto): OrderItem {
        val part = partMapper.toEntity(dto.part)
        return OrderItem(
            id = dto.id,
            orderId = dto.orderId,
            part = part,
            quantity = dto.quantity
        )
    }
}