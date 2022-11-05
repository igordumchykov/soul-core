package com.soul.core.service.dto

import com.soul.core.config.LOGIN_REGEX
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

class CreateUserRequest(

    @field:NotNull
    @field:Size(min = 1, max = 50)
    @field:Pattern(regexp = LOGIN_REGEX)
    var login: String,

    @field:Size(min = 1, max = 50)
    var firstName: String? = null,

    @field:Size(min = 1, max = 50)
    var lastName: String? = null,

    @field:NotNull
    @field:Size(min = 4, max = 100)
    var password: String,

    @field:NotNull
    @field:Size(min = 4, max = 100)
    var email: String,

    var authorities: Set<String>? = setOf()
)