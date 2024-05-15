package com.iwex.mobilepartsshop.domain.entity.recommendation

import java.io.Serializable

data class RecommendationByCriteriaRequest(
    val manufacturerId: Long,
    val deviceTypeId: Long,
    val partTypeId: Long,
    val sortAscending: Boolean,
) : Serializable