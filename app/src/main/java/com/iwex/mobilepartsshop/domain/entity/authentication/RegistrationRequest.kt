package com.iwex.mobilepartsshop.domain.entity.authentication

data class RegistrationRequest (
    val username: String,
    val password: String,
    val firstname: String,
    val lastname: String,
)