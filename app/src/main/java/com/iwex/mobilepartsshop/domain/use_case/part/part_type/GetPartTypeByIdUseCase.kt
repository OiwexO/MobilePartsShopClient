package com.iwex.mobilepartsshop.domain.use_case.part.part_type

import com.iwex.mobilepartsshop.domain.entity.part.part_type.PartType
import com.iwex.mobilepartsshop.domain.repository.part.part_type.PartTypeRepository
import javax.inject.Inject

class GetPartTypeByIdUseCase @Inject constructor(
    private val partTypeRepository: PartTypeRepository
) {
    suspend operator fun invoke(id: Long): Result<PartType> {
        return partTypeRepository.getPartType(id)
    }
}