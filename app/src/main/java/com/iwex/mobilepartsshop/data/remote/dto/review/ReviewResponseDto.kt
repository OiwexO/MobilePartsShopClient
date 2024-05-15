package com.iwex.mobilepartsshop.data.remote.dto.review

import com.google.gson.annotations.SerializedName
import com.iwex.mobilepartsshop.data.remote.dto.user.UserResponseDto
import java.util.Date

data class ReviewResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("author") val author: UserResponseDto,
    @SerializedName("partId") val partId: Long,
    @SerializedName("rating") val rating: Int,
    @SerializedName("publicationDate") val publicationDate: Date,
    @SerializedName("title") val title: String,
    @SerializedName("text") val text: String,
    @SerializedName("isEdited") val isEdited: Boolean
)