package com.iwex.mobilepartsshopstaff.domain.entity.part

import com.iwex.mobilepartsshop.domain.entity.part.DeviceType
import com.iwex.mobilepartsshop.domain.entity.part.Manufacturer
import com.iwex.mobilepartsshop.domain.entity.part.PartType
import java.io.Serializable

data class Part(
    val id: Long,
    val price: Double,
    val quantity: Int,
    val name: String,
    val deviceModels: List<String>,
    val specifications: String,
    val manufacturer: Manufacturer,
    val deviceType: DeviceType,
    val partType: PartType,
    val imageUrl: String,
) : Serializable
