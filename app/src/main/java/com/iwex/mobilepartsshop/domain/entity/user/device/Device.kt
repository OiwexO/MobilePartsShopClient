package com.iwex.mobilepartsshop.domain.entity.user.device

import com.iwex.mobilepartsshop.domain.entity.part.device_type.DeviceType
import com.iwex.mobilepartsshop.domain.entity.part.manufacturer.Manufacturer

data class Device(
    val id: Long,
    val model: String,
    val specifications: String,
    val manufacturer: Manufacturer,
    val deviceType: DeviceType,
)