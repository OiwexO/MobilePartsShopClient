package com.iwex.mobilepartsshop.data.remote.dto.mapper.part.device_type

import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseMapper
import com.iwex.mobilepartsshop.data.remote.dto.part.device_type.DeviceTypeResponseDto
import com.iwex.mobilepartsshop.domain.entity.part.device_type.DeviceType

class DeviceTypeMapper : ResponseMapper<DeviceType, DeviceTypeResponseDto>() {

    override fun toEntity(dto: DeviceTypeResponseDto): DeviceType {
        return DeviceType(
            id = dto.id,
            nameEn = dto.nameEn,
            nameUk = dto.nameUk
        )
    }
}