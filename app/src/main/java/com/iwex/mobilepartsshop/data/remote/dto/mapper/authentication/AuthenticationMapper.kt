package com.iwex.mobilepartsshop.data.remote.dto.mapper.authentication

import com.iwex.mobilepartsshop.data.remote.dto.authentication.AuthenticationRequestDto
import com.iwex.mobilepartsshop.data.remote.dto.authentication.AuthenticationResponseDto
import com.iwex.mobilepartsshop.data.remote.dto.authentication.RegistrationRequestDto
import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseRequestMapper
import com.iwex.mobilepartsshop.data.remote.dto.mapper.user.UserMapper
import com.iwex.mobilepartsshop.domain.entity.authentication.AuthenticationRequest
import com.iwex.mobilepartsshop.domain.entity.authentication.AuthenticationResponse
import com.iwex.mobilepartsshop.domain.entity.authentication.Jwt
import com.iwex.mobilepartsshop.domain.entity.authentication.RegistrationRequest
import javax.inject.Inject

class AuthenticationMapper @Inject constructor(
    private val userMapper: UserMapper
) : ResponseRequestMapper<AuthenticationResponse, AuthenticationRequest, AuthenticationResponseDto, AuthenticationRequestDto>() {

    override fun toEntity(dto: AuthenticationResponseDto): AuthenticationResponse {
        val user = userMapper.toEntity(dto.user)
        return AuthenticationResponse(
            user = user,
            jwtToken = Jwt(dto.jwtToken),
        )
    }

    override fun toRequestDto(request: AuthenticationRequest): AuthenticationRequestDto {
        return AuthenticationRequestDto(
            username = request.username,
            password = request.password,
        )
    }

    fun toRegistrationRequestDto(request: RegistrationRequest): RegistrationRequestDto {
        return RegistrationRequestDto(
            username = request.username,
            password = request.password,
            firstname = request.firstname,
            lastname = request.lastname,
        )
    }
}