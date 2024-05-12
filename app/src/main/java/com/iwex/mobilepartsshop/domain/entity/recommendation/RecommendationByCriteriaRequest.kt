package com.iwex.mobilepartsshop.domain.entity.recommendation

data class RecommendationByCriteriaRequest(
    val manufacturerId: Long,
    val deviceTypeId: Long,
    val partTypeId: Long,
    val sortAscending: Boolean,
)