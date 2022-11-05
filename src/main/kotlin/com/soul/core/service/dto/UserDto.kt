package com.soul.core.service.dto

class UserDto(
    var id: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var login: String?,
    var email: String? = null,
    var imageUrl: String? = null,
    var authorities: Set<String>? = setOf()
)