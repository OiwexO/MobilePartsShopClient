package com.iwex.mobilepartsshop.domain.entity.recommendation

data class RecommendationByDeviceRequest(
    val deviceId: Long,
    val sortAscending: Boolean,
)