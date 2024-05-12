package com.iwex.mobilepartsshop.data.remote.dto.mapper.part.manufacturer

import com.iwex.mobilepartsshop.data.remote.ApiUtils
import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseMapper
import com.iwex.mobilepartsshop.data.remote.dto.part.manufacturer.ManufacturerResponseDto
import com.iwex.mobilepartsshop.domain.entity.part.manufacturer.Manufacturer

class ManufacturerMapper : ResponseMapper<Manufacturer, ManufacturerResponseDto>() {

    override fun toEntity(dto: ManufacturerResponseDto): Manufacturer {
        return Manufacturer(
            id = dto.id,
            name = dto.name,
            logoUrl = ApiUtils.getManufacturerLogoUrl(dto.id)
        )
    }
}