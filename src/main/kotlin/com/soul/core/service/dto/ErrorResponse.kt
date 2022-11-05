package com.soul.core.service.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class ErrorResponse(
    val title: String,
    val status: Int,
    val detail: String? = null
)