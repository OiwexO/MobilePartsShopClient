package com.iwex.mobilepartsshop.data.remote.dto.authentication

import com.google.gson.annotations.SerializedName
import com.iwex.mobilepartsshop.data.remote.dto.user.UserResponseDto

data class AuthenticationResponseDto(
    @SerializedName("user") val user: UserResponseDto,
    @SerializedName("jwtToken") val jwtToken: String,
)