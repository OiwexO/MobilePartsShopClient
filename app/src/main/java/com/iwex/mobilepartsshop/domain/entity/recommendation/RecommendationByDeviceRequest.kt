package com.iwex.mobilepartsshop.domain.entity.recommendation

import java.io.Serializable

data class RecommendationByDeviceRequest(
    val deviceId: Long,
    val sortAscending: Boolean,
) : Serializable