package com.iwex.mobilepartsshop.data.remote

import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.AUTHENTICATION_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.ApiConstants.Companion.REGISTRATION_MAPPING_V1
import com.iwex.mobilepartsshop.data.remote.dto.authentication.AuthenticationRequestDto
import com.iwex.mobilepartsshop.data.remote.dto.authentication.AuthenticationResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.authentication.RegistrationRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApiService {

    @POST(REGISTRATION_MAPPING_V1)
    suspend fun registerUser(@Body requestDto: RegistrationRequestDto): AuthenticationResponseDto

    @POST(AUTHENTICATION_MAPPING_V1)
    suspend fun authenticateUser(@Body requestDto: AuthenticationRequestDto): AuthenticationResponseDto
}