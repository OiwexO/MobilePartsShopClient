package com.iwex.mobilepartsshop.data.repository.order

import com.iwex.mobilepartsshop.data.remote.MainApiService
import com.iwex.mobilepartsshop.data.remote.dto.mapper.order.OrderMapper
import com.iwex.mobilepartsshop.domain.entity.order.Order
import com.iwex.mobilepartsshop.domain.entity.order.OrderRequest
import com.iwex.mobilepartsshop.domain.repository.order.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: OrderMapper,
) : OrderRepository {

    override suspend fun getOrdersByCustomerId(customerId: Long): Result<List<Order>> {
        val response = try {
            apiService.getOrdersByCustomerId(customerId)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entities = mapper.toEntityList(response)
        return Result.success(entities)
    }

    override suspend fun getOrder(id: Long): Result<Order> {
        val response = try {
            apiService.getOrder(id)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun createOrder(customerId: Long, request: OrderRequest): Result<Order> {
        val requestDto = mapper.toRequestDto(request)
        val response = try {
            apiService.createOrder(customerId, requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun updateOrder(id: Long, request: OrderRequest): Result<Order> {
        val requestDto = mapper.toRequestDto(request)
        val response = try {
            apiService.updateOrder(id, requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun deleteOrder(id: Long): Result<Unit> {
        return try {
            apiService.deleteOrder(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}