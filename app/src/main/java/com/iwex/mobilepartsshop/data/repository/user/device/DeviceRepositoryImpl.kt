package com.iwex.mobilepartsshop.data.repository.user.device

import com.iwex.mobilepartsshop.data.remote.MainApiService
import com.iwex.mobilepartsshop.data.remote.dto.mapper.user.device.DeviceMapper
import com.iwex.mobilepartsshop.domain.entity.user.device.Device
import com.iwex.mobilepartsshop.domain.entity.user.device.DeviceRequest
import com.iwex.mobilepartsshop.domain.repository.user.DeviceRepository
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: DeviceMapper
) : DeviceRepository {

    override suspend fun getDeviceByUserId(userId: Long): Result<Device> {
        val response = try {
            apiService.getDeviceByUserId(userId)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun createDevice(userId: Long, request: DeviceRequest): Result<Device> {
        val requestDto = mapper.toRequestDto(request)
        val response = try {
            apiService.createDevice(userId, requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun updateDevice(userId: Long, request: DeviceRequest): Result<Device> {
        val requestDto = mapper.toRequestDto(request)
        val response = try {
            apiService.updateDevice(userId, requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun deleteDeviceByUserId(userId: Long): Result<Unit> {
        return try {
            apiService.deleteDeviceByUserId(userId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}