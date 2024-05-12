package com.iwex.mobilepartsshop.domain.use_case.recommendation

import com.iwex.mobilepartsshop.domain.entity.recommendation.RecommendationByCriteriaRequest
import com.iwex.mobilepartsshop.domain.repository.recommendation.RecommendationRepository
import com.iwex.mobilepartsshopstaff.domain.entity.part.Part
import javax.inject.Inject

class GetPartsByCriteriaUseCase @Inject constructor(
    private val recommendationRepository: RecommendationRepository) {

    suspend operator fun invoke(request: RecommendationByCriteriaRequest): Result<List<Part>> {
        return recommendationRepository.getPartsByCriteria(request)
    }
}