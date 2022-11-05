package com.soul.core.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable
import java.time.Instant

/**
 * Base abstract class for entities which will hold definitions for created, last modified by, created by,
 * last modified by attributes.
 */
abstract class AbstractAuditingEntity(

    @CreatedBy
    @Field
    @JsonIgnore
    var createdBy: String? = null,

    @CreatedDate
    @Field
    @JsonIgnore
    var createdDate: Instant? = Instant.now(),

    @LastModifiedBy
    @Field
    @JsonIgnore
    var lastModifiedBy: String? = null,

    @LastModifiedDate
    @Field
    @JsonIgnore
    var lastModifiedDate: Instant? = Instant.now()

) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }
}
