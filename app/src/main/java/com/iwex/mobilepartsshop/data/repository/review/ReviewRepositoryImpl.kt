package com.iwex.mobilepartsshop.data.repository.review

import com.iwex.mobilepartsshop.data.remote.MainApiService
import com.iwex.mobilepartsshop.data.remote.dto.mapper.review.ReviewMapper
import com.iwex.mobilepartsshop.domain.entity.review.Review
import com.iwex.mobilepartsshop.domain.entity.review.ReviewRequest
import com.iwex.mobilepartsshop.domain.repository.review.ReviewRepository
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val apiService: MainApiService,
    private val mapper: ReviewMapper
) : ReviewRepository {

    override suspend fun getReviewsByPartId(partId: Long): Result<List<Review>> {
        val response = try {
            apiService.getAllReviewsForPart(partId)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entities = mapper.toEntityList(response)
        return Result.success(entities)
    }

    override suspend fun getReviewById(id: Long): Result<Review> {
        val response = try {
            apiService.getReview(id)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun createReview(request: ReviewRequest): Result<Review> {
        val requestDto = mapper.toRequestDto(request)
        val response = try {
            apiService.createReview(requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun updateReview(id: Long, request: ReviewRequest): Result<Review> {
        val requestDto = mapper.toRequestDto(request)
        val response = try {
            apiService.updateReview(id, requestDto)
        } catch (e: Exception) {
            return Result.failure(e)
        }
        val entity = mapper.toEntity(response)
        return Result.success(entity)
    }

    override suspend fun deleteReview(id: Long): Result<Unit> {
        return try {
            apiService.deleteReview(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}