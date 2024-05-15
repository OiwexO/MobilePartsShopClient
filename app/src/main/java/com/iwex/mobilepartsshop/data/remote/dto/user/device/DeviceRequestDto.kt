package com.iwex.mobilepartsshop.data.remote.dto.user.device

import com.google.gson.annotations.SerializedName

data class DeviceRequestDto(
    @SerializedName("model") val model: String,
    @SerializedName("specifications") val specifications: String,
    @SerializedName("manufacturerId") val manufacturerId: Long,
    @SerializedName("deviceTypeId") val deviceTypeId: Long
)