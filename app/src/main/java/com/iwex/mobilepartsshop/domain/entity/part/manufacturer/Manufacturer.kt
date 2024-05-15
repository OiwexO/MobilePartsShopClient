package com.iwex.mobilepartsshop.domain.entity.part.manufacturer

import java.io.Serializable

data class Manufacturer(
    val id: Long,
    val name: String,
    val logoUrl: String,
) : Serializable