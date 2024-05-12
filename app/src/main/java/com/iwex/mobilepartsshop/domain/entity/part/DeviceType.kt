package com.iwex.mobilepartsshop.domain.entity.part

import java.io.Serializable

data class DeviceType(
    val id: Long,
    val nameEn: String,
    val nameUk: String,
) : Serializable
