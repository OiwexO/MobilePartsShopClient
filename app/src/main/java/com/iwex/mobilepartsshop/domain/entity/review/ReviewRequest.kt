package com.iwex.mobilepartsshop.domain.entity.review

data class ReviewRequest(
    val authorId: Long,
    val partId: Long,
    val rating: Int,
    val title: String,
    val text: String,
)