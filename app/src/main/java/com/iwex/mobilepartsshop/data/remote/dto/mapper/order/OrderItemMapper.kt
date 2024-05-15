package com.iwex.mobilepartsshop.data.remote.dto.mapper.order

import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseRequestMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.PartMapper
import com.iwex.mobilepartsshop.data.remote.dto.order.OrderItemRequestDto
import com.iwex.mobilepartsshop.data.remote.dto.order.OrderItemResponseDto
import com.iwex.mobilepartsshop.domain.entity.order.OrderItem
import com.iwex.mobilepartsshop.domain.entity.order.OrderItemRequest
import javax.inject.Inject

class OrderItemMapper @Inject constructor(
    private val partMapper: PartMapper
) : ResponseRequestMapper<OrderItem, OrderItemRequest, OrderItemResponseDto, OrderItemRequestDto>() {

    override fun toEntity(dto: OrderItemResponseDto): OrderItem {
        val part = partMapper.toEntity(dto.part)
        return OrderItem(
            id = dto.id,
            orderId = dto.orderId,
            part = part,
            quantity = dto.quantity
        )
    }

    override fun toRequestDto(request: OrderItemRequest): OrderItemRequestDto {
        return OrderItemRequestDto(
            partId = request.partId,
            quantity = request.quantity
        )
    }

    fun toRequestDtoList(requests: List<OrderItemRequest>): List<OrderItemRequestDto> {
        return requests.map { toRequestDto(it) }
    }
}