package com.iwex.mobilepartsshop.data.remote.dto.mapper.part

import com.iwex.mobilepartsshop.data.remote.ApiUtils
import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.device_type.DeviceTypeMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.manufacturer.ManufacturerMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.part_type.PartTypeMapper
import com.iwex.mobilepartsshop.data.remote.dto.part.PartResponseDto
import com.iwex.mobilepartsshop.domain.entity.part.Part
import javax.inject.Inject

class PartMapper @Inject constructor(
    private val manufacturerMapper: ManufacturerMapper,
    private val deviceTypeMapper: DeviceTypeMapper,
    private val partTypeMapper: PartTypeMapper,
) : ResponseMapper<Part, PartResponseDto>() {

    override fun toEntity(dto: PartResponseDto): Part {
        val manufacturer = manufacturerMapper.toEntity(dto.manufacturer)
        val deviceType = deviceTypeMapper.toEntity(dto.deviceType)
        val partType = partTypeMapper.toEntity(dto.partType)
        return Part(
            id = dto.id,
            price = dto.price,
            quantity = dto.quantity,
            name = dto.name,
            deviceModels = dto.deviceModels,
            specifications = dto.specifications,
            manufacturer = manufacturer,
            deviceType = deviceType,
            partType = partType,
            imageUrl = ApiUtils.getPartImageUrl(dto.id)
        )
    }
}