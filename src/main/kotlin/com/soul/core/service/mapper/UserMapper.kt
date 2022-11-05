package com.soul.core.service.mapper

import com.soul.core.domain.User
import com.soul.core.service.dto.UserDto
import org.springframework.stereotype.Service

@Service
class UserMapper {

    fun toDtoList(users: List<User?>): List<UserDto> =
        users.asSequence().filterNotNull().mapTo(mutableListOf()) { toDto(it) }

    fun toDto(user: User): UserDto = UserDto(
        id = user.id,
        login = user.login,
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email,
        imageUrl = user.imageUrl,
        authorities = user.authorities
    )
}