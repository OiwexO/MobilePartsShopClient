package com.iwex.mobilepartsshop.domain.repository.authentication

import com.iwex.mobilepartsshop.domain.entity.authentication.AuthenticationRequest
import com.iwex.mobilepartsshop.domain.entity.authentication.AuthenticationResponse
import com.iwex.mobilepartsshop.domain.entity.authentication.Jwt
import com.iwex.mobilepartsshop.domain.entity.authentication.RegistrationRequest
import com.iwex.mobilepartsshop.domain.entity.user.User

interface AuthenticationRepository {

    suspend fun registerUser(registrationRequest: RegistrationRequest): Result<AuthenticationResponse>

    suspend fun authenticateUser(authenticationRequest: AuthenticationRequest): Result<AuthenticationResponse>

    suspend fun isAuthenticated(): Result<Boolean>

    suspend fun getJwt(): Jwt

    suspend fun getUser(): Result<User>

    suspend fun logout(): Result<Unit>
}