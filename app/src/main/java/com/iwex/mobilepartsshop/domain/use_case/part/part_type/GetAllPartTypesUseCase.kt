package com.iwex.mobilepartsshop.domain.use_case.part.part_type

import com.iwex.mobilepartsshop.domain.entity.part.part_type.PartType
import com.iwex.mobilepartsshop.domain.repository.part.PartTypeRepository
import javax.inject.Inject

class GetAllPartTypesUseCase @Inject constructor(
    private val partTypeRepository: PartTypeRepository
) {
    suspend operator fun invoke(): Result<List<PartType>> {
        return partTypeRepository.getAllPartTypes()
    }
}