package com.iwex.mobilepartsshop.data.repository.part

import com.iwex.mobilepartsshop.data.remote.MainApiService
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.PartMapper
import com.iwex.mobilepartsshop.domain.entity.part.Part
import com.iwex.mobilepartsshop.domain.repository.part.PartRepository
import javax.inject.Inject

class PartRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: PartMapper
) : PartRepository {

    override suspend fun getAllParts(): Result<List<Part>> {
        val response = try {
            apiService.getAllParts()
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entities = mapper.toEntityList(response)
        return Result.success(entities)
    }

    override suspend fun getPart(id: Long): Result<Part> {
        val response = try {
            apiService.getPart(id)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }
}