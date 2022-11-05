package com.soul.core.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field

abstract class BaseMenu(
    @Id
    var id: String? = null,

    @Field
    var name: LangString? = LangString(),

    @Field
    var description: LangString? = LangString(),

    @Field
    var imageUrl: String?,

    @Field
    var groups: ArrayList<MenuGroup>?
)