package com.iwex.mobilepartsshop.domain.entity.review

import java.util.Date

data class ReviewRequest(
    val authorId: Long,
    val partId: Long,
    val rating: Int,
    val publicationDate: Date,
    val title: String,
    val text: String,
)