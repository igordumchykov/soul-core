package com.soul.core.service.dto

import javax.validation.constraints.NotNull

class RenameImageRequest(
    @field:NotNull(message = "oldName should not be empty")
    var oldName: String? = null,
    @field:NotNull(message = "newName should not be empty")
    var newName: String? = null
)