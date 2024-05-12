package com.iwex.mobilepartsshop.domain.use_case.review

import com.iwex.mobilepartsshop.domain.repository.review.ReviewRepository
import javax.inject.Inject

class DeleteReviewUseCase @Inject constructor(private val reviewRepository: ReviewRepository) {

    suspend operator fun invoke(reviewId: Long): Result<Unit> {
        return reviewRepository.deleteReview(reviewId)
    }
}
