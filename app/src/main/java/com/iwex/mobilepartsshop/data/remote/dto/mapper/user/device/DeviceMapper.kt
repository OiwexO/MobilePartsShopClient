package com.iwex.mobilepartsshop.data.remote.dto.mapper.user.device

import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseRequestMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.device_type.DeviceTypeMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.part.manufacturer.ManufacturerMapper
import com.iwex.mobilepartsshop.data.remote.dto.user.device.DeviceRequestDto
import com.iwex.mobilepartsshop.data.remote.dto.user.device.DeviceResponseDto
import com.iwex.mobilepartsshop.domain.entity.user.device.Device
import com.iwex.mobilepartsshop.domain.entity.user.device.DeviceRequest
import javax.inject.Inject

class DeviceMapper @Inject constructor(
    private val manufacturerMapper: ManufacturerMapper,
    private val deviceTypeMapper: DeviceTypeMapper
) : ResponseRequestMapper<Device, DeviceRequest, DeviceResponseDto, DeviceRequestDto>() {

    override fun toEntity(dto: DeviceResponseDto): Device {
        return Device(
            id = dto.id,
            model = dto.model,
            specifications = dto.specifications,
            manufacturer = manufacturerMapper.toEntity(dto.manufacturer),
            deviceType = deviceTypeMapper.toEntity(dto.deviceType)
        )
    }

    override fun toRequestDto(request: DeviceRequest): DeviceRequestDto {
        return DeviceRequestDto(
            model = request.model,
            specifications = request.specifications,
            manufacturerId = request.manufacturerId,
            deviceTypeId = request.deviceTypeId
        )
    }
}