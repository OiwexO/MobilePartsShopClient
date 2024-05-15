package com.iwex.mobilepartsshop.domain.entity.part.part_type

import java.io.Serializable

data class PartType(
    val id: Long,
    val nameEn: String,
    val nameUk: String,
) : Serializable