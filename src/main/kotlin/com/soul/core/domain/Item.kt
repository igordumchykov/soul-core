package com.soul.core.domain

import org.springframework.data.mongodb.core.mapping.Field

class Item(

    @Field
    var subId: String? = null,

    @Field
    var name: LangString? = LangString(),

    @Field
    var description: LangString? = LangString(),

    @Field
    var priceUnit: LangString? = LangString(),

    @Field
    var volumeUnit: LangString? = LangString(),

    @Field
    var prices: List<PriceAndVolume>? = listOf(),

    @Field
    var available: Boolean? = true,

    @Field
    var imageUrl: String? = "",

    @Field
    var tags: Set<String>?,

    @Field
    var ingredients: List<Ingredient>?,

    @Field
    var extra: List<Ingredient>?,
)