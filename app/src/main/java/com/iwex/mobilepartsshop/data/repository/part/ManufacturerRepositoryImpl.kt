package com.iwex.mobilepartsshop.data.repository.part

import com.iwex.mobilepartsshop.data.remote.MainApiService
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.manufacturer.ManufacturerMapper
import com.iwex.mobilepartsshop.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshop.domain.repository.part.manufacturer.ManufacturerRepository
import javax.inject.Inject

class ManufacturerRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: ManufacturerMapper
) : ManufacturerRepository {

    override suspend fun getAllManufacturers(): Result<List<Manufacturer>> {
        val response = try {
            apiService.getAllManufacturers()
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entities = mapper.toEntityList(response)
        return Result.success(entities)
    }

    override suspend fun getManufacturer(id: Long): Result<Manufacturer> {
        val response = try {
            apiService.getManufacturer(id)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }
}