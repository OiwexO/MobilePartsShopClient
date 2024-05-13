package com.iwex.mobilepartsshop.domain.use_case.recommendation

import com.iwex.mobilepartsshop.domain.entity.recommendation.RecommendationByDeviceRequest
import com.iwex.mobilepartsshop.domain.repository.recommendation.RecommendationRepository
import com.iwex.mobilepartsshop.domain.entity.part.Part
import javax.inject.Inject

class GetPartsByDeviceUseCase @Inject constructor(
    private val recommendationRepository: RecommendationRepository
) {

    suspend operator fun invoke(request: RecommendationByDeviceRequest): Result<List<Part>> {
        return recommendationRepository.getPartsByDevice(request)
    }
}
