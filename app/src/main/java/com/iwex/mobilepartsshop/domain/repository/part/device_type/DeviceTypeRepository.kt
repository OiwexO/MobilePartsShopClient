package com.iwex.mobilepartsshop.domain.repository.part.device_type

import com.iwex.mobilepartsshop.domain.entity.part.device_type.DeviceType

interface DeviceTypeRepository {

    suspend fun getAllDeviceTypes(): Result<List<DeviceType>>

    suspend fun getDeviceType(id: Long): Result<DeviceType>
}
