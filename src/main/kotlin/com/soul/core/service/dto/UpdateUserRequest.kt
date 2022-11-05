package com.soul.core.service.dto

import javax.validation.constraints.Size

class UpdateUserRequest(

    @field:Size(min = 1, max = 50)
    var firstName: String? = null,

    @field:Size(min = 1, max = 50)
    var lastName: String? = null,

    @field:Size(min = 4, max = 100)
    var email: String? = null,

    var authorities: Set<String>? = setOf()
)