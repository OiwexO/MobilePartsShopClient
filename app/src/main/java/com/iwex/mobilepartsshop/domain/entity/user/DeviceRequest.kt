package com.iwex.mobilepartsshop.domain.entity.user

data class DeviceRequest(
    val model: String,
    val specifications: String,
    val manufacturerId: Long,
    val deviceTypeId: Long,
)