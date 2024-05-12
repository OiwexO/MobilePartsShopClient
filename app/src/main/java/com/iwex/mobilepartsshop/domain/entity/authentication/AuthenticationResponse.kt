package com.iwex.mobilepartsshop.domain.entity.authentication

import com.iwex.mobilepartsshopstaff.domain.entity.user.User

data class AuthenticationResponse(
    val user: User,
    val jwtToken: Jwt,
)