package com.iwex.mobilepartsshop.domain.use_case.review

import com.iwex.mobilepartsshop.domain.entity.review.Review
import com.iwex.mobilepartsshop.domain.repository.review.ReviewRepository
import javax.inject.Inject

class GetReviewByIdUseCase @Inject constructor(private val reviewRepository: ReviewRepository) {

    suspend operator fun invoke(id: Long): Result<Review> {
        return reviewRepository.getReviewById(id)
    }
}