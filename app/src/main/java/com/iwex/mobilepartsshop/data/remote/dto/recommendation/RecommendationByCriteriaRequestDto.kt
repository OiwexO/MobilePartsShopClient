package com.iwex.mobilepartsshop.data.remote.dto.recommendation

import com.google.gson.annotations.SerializedName

data class RecommendationByCriteriaRequestDto(
    @SerializedName("manufacturerId") val manufacturerId: Long,
    @SerializedName("deviceTypeId") val deviceTypeId: Long,
    @SerializedName("partTypeId") val partTypeId: Long,
    @SerializedName("sortAscending") val sortAscending: Boolean
)