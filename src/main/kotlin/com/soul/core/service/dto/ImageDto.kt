package com.soul.core.service.dto

import java.util.*
import javax.validation.constraints.NotNull

class ImageDto(
    var bucketName: String? = null,
    @field:NotNull(message = "key should not be empty")
    var key: String? = null,
    var size: Long? = null,
    var lastModified: Date? = null
)