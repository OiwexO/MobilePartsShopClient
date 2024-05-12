package com.iwex.mobilepartsshop.domain.repository.user

import com.iwex.mobilepartsshop.domain.entity.user.Device
import com.iwex.mobilepartsshop.domain.entity.user.DeviceRequest

interface DeviceRepository {

    fun getDeviceByUserId(userId: Long): Result<Device>

    fun createDevice(userId: Long, request: DeviceRequest): Result<Device>

    fun updateDevice(userId: Long, request: DeviceRequest): Result<Device>

    fun deleteDeviceByUserId(userId: Long): Result<Unit>
}