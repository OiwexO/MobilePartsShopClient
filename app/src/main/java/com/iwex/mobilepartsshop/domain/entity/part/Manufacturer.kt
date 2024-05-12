package com.iwex.mobilepartsshop.domain.entity.part

import java.io.Serializable

data class Manufacturer(
    val id: Long,
    val name: String,
    val logoUrl: String,
) : Serializable
