package com.iwex.mobilepartsshop.domain.repository.review

import com.iwex.mobilepartsshop.domain.entity.review.Review
import com.iwex.mobilepartsshop.domain.entity.review.ReviewRequest

interface ReviewRepository {

    suspend fun getReviewsByPartId(partId: Long): Result<List<Review>>

    suspend fun getReviewById(reviewId: Long): Result<Review>

    suspend fun createReview(request: ReviewRequest): Result<Review>

    suspend fun updateReview(reviewId: Long, request: ReviewRequest): Result<Review>

    suspend fun deleteReview(reviewId: Long): Result<Unit>
}