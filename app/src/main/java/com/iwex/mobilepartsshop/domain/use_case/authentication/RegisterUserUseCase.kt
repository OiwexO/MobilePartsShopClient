package com.iwex.mobilepartsshop.domain.use_case.authentication

import com.iwex.mobilepartsshop.domain.entity.authentication.AuthenticationResponse
import com.iwex.mobilepartsshop.domain.entity.authentication.RegistrationRequest
import com.iwex.mobilepartsshop.domain.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend operator fun invoke(
        registrationRequest: RegistrationRequest
    ): Result<AuthenticationResponse> {
        return authenticationRepository.registerUser(registrationRequest)
    }
}