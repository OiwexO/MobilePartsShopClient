package com.iwex.mobilepartsshop.domain.repository.part

import com.iwex.mobilepartsshop.domain.entity.part.DeviceType

interface DeviceTypeRepository {

    suspend fun getAllDeviceTypes(): Result<List<DeviceType>>

    suspend fun getDeviceType(id: Long): Result<DeviceType>
}
