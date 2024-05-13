package com.iwex.mobilepartsshop.domain.use_case.review

import com.iwex.mobilepartsshop.domain.entity.review.Review
import com.iwex.mobilepartsshop.domain.entity.review.ReviewRequest
import com.iwex.mobilepartsshop.domain.repository.review.ReviewRepository
import javax.inject.Inject

class UpdateReviewUseCase @Inject constructor(private val reviewRepository: ReviewRepository) {

    suspend operator fun invoke(id: Long, request: ReviewRequest): Result<Review> {
        return reviewRepository.updateReview(id, request)
    }
}