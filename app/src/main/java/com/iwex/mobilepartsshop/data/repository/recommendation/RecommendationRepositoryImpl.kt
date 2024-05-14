package com.iwex.mobilepartsshop.data.repository.recommendation

import com.iwex.mobilepartsshop.data.remote.MainApiService
import com.iwex.mobilepartsshop.data.remote.dto.mapper.recommendation.RecommendationByCriteriaMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.recommendation.RecommendationByDeviceMapper
import com.iwex.mobilepartsshop.domain.entity.part.Part
import com.iwex.mobilepartsshop.domain.entity.recommendation.RecommendationByCriteriaRequest
import com.iwex.mobilepartsshop.domain.entity.recommendation.RecommendationByDeviceRequest
import com.iwex.mobilepartsshop.domain.repository.recommendation.RecommendationRepository
import javax.inject.Inject

class RecommendationRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val recommendationByCriteriaMapper: RecommendationByCriteriaMapper,
    private val recommendationByDeviceMapper: RecommendationByDeviceMapper
) : RecommendationRepository {

    override suspend fun getPartsByCriteria(request: RecommendationByCriteriaRequest): Result<List<Part>> {
        val requestDto = recommendationByCriteriaMapper.toRequestDto(request)
        val response = try {
            apiService.getPartsByCriteria(requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entities = recommendationByCriteriaMapper.toEntityList(response)
        return Result.success(entities)
    }

    override suspend fun getPartsByDevice(request: RecommendationByDeviceRequest): Result<List<Part>> {
        val requestDto = recommendationByDeviceMapper.toRequestDto(request)
        val response = try {
            apiService.getPartsByDevice(requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entities = recommendationByDeviceMapper.toEntityList(response)
        return Result.success(entities)
    }
}