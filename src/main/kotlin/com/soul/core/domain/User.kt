package com.soul.core.domain

import com.soul.core.config.TableNames
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(TableNames.USER)
class User(
    var id: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    @Indexed
    var login: String?,
    var password: String?,
    @Indexed
    var email: String? = null,
    var imageUrl: String? = null,
    var authorities: Set<String>? = setOf()
)