package com.iwex.mobilepartsshop.data.remote.dto.mapper.user

import com.iwex.mobilepartsshop.data.remote.dto.mapper.ResponseMapper
import com.iwex.mobilepartsshop.data.remote.dto.user.UserResponseDto
import com.iwex.mobilepartsshop.domain.entity.user.User

class UserMapper : ResponseMapper<User, UserResponseDto>() {

    override fun toEntity(dto: UserResponseDto): User {
        return User(
            id = dto.id,
            username = dto.username,
            firstname = dto.firstname,
            lastname = dto.lastname,
            authority = dto.authority
        )
    }
}