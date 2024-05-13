package com.iwex.mobilepartsshop.domain.entity.user.device

data class DeviceRequest(
    val model: String,
    val specifications: String,
    val manufacturerId: Long,
    val deviceTypeId: Long,
)