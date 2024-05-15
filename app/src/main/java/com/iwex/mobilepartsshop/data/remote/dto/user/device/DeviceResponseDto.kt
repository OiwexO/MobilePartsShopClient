package com.iwex.mobilepartsshop.data.remote.dto.user.device

import com.google.gson.annotations.SerializedName
import com.iwex.mobilepartsshop.data.remote.dto.part.device_type.DeviceTypeResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.part.manufacturer.ManufacturerResponseDto

data class DeviceResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("model") val model: String,
    @SerializedName("specifications") val specifications: String,
    @SerializedName("manufacturer") val manufacturer: ManufacturerResponseDto,
    @SerializedName("deviceType") val deviceType: DeviceTypeResponseDto
)