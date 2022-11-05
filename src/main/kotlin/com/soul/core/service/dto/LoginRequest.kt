package com.soul.core.service.dto

import javax.validation.constraints.NotEmpty

class LoginRequest(

    @field:NotEmpty(message = "login can not be empty")
    val login: String?,

    @field:NotEmpty(message = "password can not be empty")
    val password: String?,

    val isRememberMe: Boolean
)