package com.soul.core.domain

import org.springframework.data.mongodb.core.mapping.Field

class MenuGroup(

    @Field
    var subId: String? = null,

    @Field
    var category: String? = String(),

    @Field
    var name: LangString? = LangString(),

    @Field
    var description: LangString? = LangString(),

    @Field
    var imageUrl: String?,

    @Field
    var available: Boolean? = true,

    @Field
    var groups: ArrayList<MenuGroup>?,

    @Field
    var items: ArrayList<Item>?,

    @Field
    var tags: Set<String>?,
)