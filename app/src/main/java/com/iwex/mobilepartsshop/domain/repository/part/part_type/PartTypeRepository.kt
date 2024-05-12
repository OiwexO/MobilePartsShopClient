package com.iwex.mobilepartsshop.domain.repository.part

import com.iwex.mobilepartsshop.domain.entity.part.part_type.PartType

interface PartTypeRepository {

    suspend fun getAllPartTypes(): Result<List<PartType>>

    suspend fun getPartType(id: Long): Result<PartType>
}