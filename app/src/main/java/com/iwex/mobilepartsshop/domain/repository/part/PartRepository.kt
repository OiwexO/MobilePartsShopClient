package com.iwex.mobilepartsshop.domain.repository.part

import com.iwex.mobilepartsshopstaff.domain.entity.part.Part

interface PartRepository {

    suspend fun getAllParts(): Result<List<Part>>

    suspend fun getPart(id: Long): Result<Part>
}