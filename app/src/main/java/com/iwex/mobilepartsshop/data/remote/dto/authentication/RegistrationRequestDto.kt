package com.iwex.mobilepartsshop.data.remote.dto.authentication

import com.google.gson.annotations.SerializedName

data class RegistrationRequestDto(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("firstname") val firstname: String,
    @SerializedName("lastname") val lastname: String,
)