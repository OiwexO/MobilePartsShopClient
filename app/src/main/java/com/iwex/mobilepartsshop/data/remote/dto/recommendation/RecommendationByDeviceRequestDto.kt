package com.iwex.mobilepartsshop.data.remote.dto.recommendation

import com.google.gson.annotations.SerializedName

data class RecommendationByDeviceRequestDto(
    @SerializedName("deviceId") val deviceId: Long,
    @SerializedName("sortAscending") val sortAscending: Boolean
)