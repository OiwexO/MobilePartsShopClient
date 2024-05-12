package com.iwex.mobilepartsshop.domain.entity.user

import com.iwex.mobilepartsshop.domain.entity.part.DeviceType
import com.iwex.mobilepartsshop.domain.entity.part.Manufacturer

data class Device(
    val id: Long,
    val model: String,
    val specifications: String,
    val manufacturer: Manufacturer,
    val deviceType: DeviceType,
)