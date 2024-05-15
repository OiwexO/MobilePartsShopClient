package com.iwex.mobilepartsshop.domain.repository.recommendation

import com.iwex.mobilepartsshop.domain.entity.part.Part
import com.iwex.mobilepartsshop.domain.entity.recommendation.RecommendationByCriteriaRequest
import com.iwex.mobilepartsshop.domain.entity.recommendation.RecommendationByDeviceRequest

interface RecommendationRepository {

    suspend fun getPartsByCriteria(request: RecommendationByCriteriaRequest): Result<List<Part>>

    suspend fun getPartsByDevice(request: RecommendationByDeviceRequest): Result<List<Part>>
}