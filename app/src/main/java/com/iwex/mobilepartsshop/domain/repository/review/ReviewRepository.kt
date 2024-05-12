package com.iwex.mobilepartsshop.domain.repository.review

import com.iwex.mobilepartsshop.domain.entity.review.Review
import com.iwex.mobilepartsshop.domain.entity.review.ReviewRequest

interface ReviewRepository {

    fun getReviewsByPartId(partId: Long): Result<List<Review>>

    fun getReviewById(reviewId: Long): Result<Review>

    fun createReview(request: ReviewRequest): Result<Review>

    fun updateReview(reviewId: Long, request: ReviewRequest): Result<Review>

    fun deleteReview(reviewId: Long): Result<Unit>
}