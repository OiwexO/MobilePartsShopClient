package com.iwex.mobilepartsshop.data.remote.dto.mapper.review

import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseRequestMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.user.UserMapper
import com.iwex.mobilepartsshop.data.remote.dto.review.ReviewRequestDto
import com.iwex.mobilepartsshop.data.remote.dto.review.ReviewResponseDto
import com.iwex.mobilepartsshop.domain.entity.review.Review
import com.iwex.mobilepartsshop.domain.entity.review.ReviewRequest
import javax.inject.Inject

class ReviewMapper @Inject constructor(
    private val userMapper: UserMapper
) : ResponseRequestMapper<Review, ReviewRequest, ReviewResponseDto, ReviewRequestDto>() {

    override fun toEntity(dto: ReviewResponseDto): Review {
        val author = userMapper.toEntity(dto.author)
        return Review(
            id = dto.id,
            author = author,
            partId = dto.partId,
            rating = dto.rating,
            publicationDate = dto.publicationDate,
            title = dto.title,
            text = dto.text,
            isEdited = dto.isEdited
        )
    }

    override fun toRequestDto(request: ReviewRequest): ReviewRequestDto {
        return ReviewRequestDto(
            authorId = request.authorId,
            partId = request.partId,
            rating = request.rating,
            title = request.title,
            text = request.text
        )
    }
}