package com.iwex.mobilepartsshop.domain.repository.user

import com.iwex.mobilepartsshop.domain.entity.user.device.Device
import com.iwex.mobilepartsshop.domain.entity.user.device.DeviceRequest

interface DeviceRepository {

    suspend fun getDeviceByUserId(userId: Long): Result<Device>

    suspend fun createDevice(userId: Long, request: DeviceRequest): Result<Device>

    suspend fun updateDevice(userId: Long, request: DeviceRequest): Result<Device>

    suspend fun deleteDeviceByUserId(userId: Long): Result<Unit>
}