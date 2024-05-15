package com.iwex.mobilepartsshop.data.remote.dto.user

import com.google.gson.annotations.SerializedName
import com.iwex.mobilepartsshop.domain.entity.user.UserAuthority

data class UserResponseDto(
    @SerializedName("id") val id: Long,
    @SerializedName("username") val username: String,
    @SerializedName("firstname") val firstname: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("authority") val authority: UserAuthority,
)
