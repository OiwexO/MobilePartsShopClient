package com.iwex.mobilepartsshop.data.remote.dto.mapper.part.part_type

import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseMapper
import com.iwex.mobilepartsshop.data.remote.dto.part.part_type.PartTypeResponseDto
import com.iwex.mobilepartsshop.domain.entity.part.part_type.PartType

class PartTypeMapper : ResponseMapper<PartType, PartTypeResponseDto>() {

    override fun toEntity(dto: PartTypeResponseDto): PartType {
        return PartType(
            id = dto.id,
            nameEn = dto.nameEn,
            nameUk = dto.nameUk
        )
    }
}