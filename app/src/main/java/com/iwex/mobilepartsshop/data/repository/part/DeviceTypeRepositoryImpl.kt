package com.iwex.mobilepartsshop.data.repository.part

import com.iwex.mobilepartsshop.data.remote.MainApiService
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.device_type.DeviceTypeMapper
import com.iwex.mobilepartsshop.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshop.domain.repository.part.device_type.DeviceTypeRepository
import javax.inject.Inject

class DeviceTypeRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: DeviceTypeMapper,
) : DeviceTypeRepository {

    override suspend fun getAllDeviceTypes(): Result<List<DeviceType>> {
        val response = try {
            apiService.getAllDeviceTypes()
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entities = mapper.toEntityList(response)
        return Result.success(entities)
    }

    override suspend fun getDeviceType(id: Long): Result<DeviceType> {
        TODO("Not yet implemented")
    }
}