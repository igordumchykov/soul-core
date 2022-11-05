package com.soul.core.domain

import org.springframework.data.mongodb.core.mapping.Field
import java.math.BigDecimal

class PriceAndVolume(

    @Field
    var description: LangString? = LangString(),

    @Field
    var volume: Int?,

    @Field
    var price: BigDecimal?,
)