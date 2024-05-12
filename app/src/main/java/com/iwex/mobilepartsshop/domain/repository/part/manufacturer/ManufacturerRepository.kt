package com.iwex.mobilepartsshop.domain.repository.part

import com.iwex.mobilepartsshop.domain.entity.part.Manufacturer

interface ManufacturerRepository {

    suspend fun getAllManufacturers(): Result<List<Manufacturer>>

    suspend fun getManufacturer(id: Long): Result<Manufacturer>
}