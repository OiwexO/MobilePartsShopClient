package com.iwex.mobilepartsshop.domain.entity.review

import com.iwex.mobilepartsshop.domain.entity.user.User
import java.util.Date

data class Review(
    val id: Long,
    val author: User,
    val partId: Long,
    val rating: Int,
    val publicationDate: Date,
    val title: String,
    val text: String,
    val isEdited: Boolean,
)