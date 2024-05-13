package com.iwex.mobilepartsshop.data.remote.dto.review

import com.google.gson.annotations.SerializedName

data class ReviewRequestDto(
    @SerializedName("authorId") val authorId: Long,
    @SerializedName("partId") val partId: Long,
    @SerializedName("rating") val rating: Int,
    @SerializedName("title") val title: String,
    @SerializedName("text") val text: String
)