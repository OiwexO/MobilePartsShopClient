package com.iwex.mobilepartsshop.domain.use_case.part.manufacturer

import com.iwex.mobilepartsshop.domain.entity.part.manufacturer.Manufacturer
import com.iwex.mobilepartsshop.domain.repository.part.manufacturer.ManufacturerRepository
import javax.inject.Inject

class GetManufacturerByIdUseCase @Inject constructor(
    private val manufacturerRepository: ManufacturerRepository
) {
    suspend operator fun invoke(id: Long): Result<Manufacturer> {
        return manufacturerRepository.getManufacturer(id)
    }
}