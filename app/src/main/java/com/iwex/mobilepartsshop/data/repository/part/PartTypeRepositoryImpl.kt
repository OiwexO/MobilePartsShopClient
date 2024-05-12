package com.iwex.mobilepartsshop.data.repository.part

import com.iwex.mobilepartsshop.data.remote.MainApiService
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.part_type.PartTypeMapper
import com.iwex.mobilepartsshop.domain.entity.part.part_type.PartType
import com.iwex.mobilepartsshop.domain.repository.part.PartTypeRepository
import javax.inject.Inject

class PartTypeRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: PartTypeMapper,
) : PartTypeRepository {

    override suspend fun getAllPartTypes(): Result<List<PartType>> {
        val response = try {
            apiService.getAllPartTypes()
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entities = mapper.toEntityList(response)
        return Result.success(entities)
    }

    override suspend fun getPartType(id: Long): Result<PartType> {
        TODO("Not yet implemented")
    }
}