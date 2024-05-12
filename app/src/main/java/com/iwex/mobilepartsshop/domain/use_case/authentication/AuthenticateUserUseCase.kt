package com.iwex.mobilepartsshop.domain.use_case.authentication

import com.iwex.mobilepartsshop.domain.entity.authentication.AuthenticationRequest
import com.iwex.mobilepartsshop.domain.entity.authentication.AuthenticationResponse
import com.iwex.mobilepartsshop.domain.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend operator fun invoke(
        authenticationRequest: AuthenticationRequest
    ): Result<AuthenticationResponse> {
        return authenticationRepository.authenticateUser(authenticationRequest)
    }
}

