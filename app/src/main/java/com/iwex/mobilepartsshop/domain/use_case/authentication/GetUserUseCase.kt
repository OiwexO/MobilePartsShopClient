package com.iwex.mobilepartsshop.domain.use_case.authentication

import com.iwex.mobilepartsshop.domain.entity.user.User
import com.iwex.mobilepartsshop.domain.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend operator fun invoke(): Result<User> {
        return authenticationRepository.getUser()
    }
}