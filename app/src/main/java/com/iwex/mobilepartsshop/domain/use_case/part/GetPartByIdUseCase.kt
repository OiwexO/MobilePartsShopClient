package com.iwex.mobilepartsshop.domain.use_case.part

import com.iwex.mobilepartsshop.domain.repository.part.PartRepository
import com.iwex.mobilepartsshopstaff.domain.entity.part.Part
import javax.inject.Inject

class GetPartByIdUseCase @Inject constructor(
    private val partRepository: PartRepository
) {
    suspend operator fun invoke(id: Long): Result<Part> {
        return partRepository.getPart(id)
    }
}