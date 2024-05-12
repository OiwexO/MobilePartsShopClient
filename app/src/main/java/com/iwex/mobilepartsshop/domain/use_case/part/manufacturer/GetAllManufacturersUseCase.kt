package com.iwex.mobilepartsshop.domain.use_case.part.manufacturer

import com.iwex.mobilepartsshop.domain.entity.part.Manufacturer
import com.iwex.mobilepartsshop.domain.repository.part.ManufacturerRepository
import javax.inject.Inject

class GetAllManufacturersUseCase @Inject constructor(
    private val manufacturerRepository: ManufacturerRepository
) {
    suspend operator fun invoke(): Result<List<Manufacturer>> {
        return manufacturerRepository.getAllManufacturers()
    }
}