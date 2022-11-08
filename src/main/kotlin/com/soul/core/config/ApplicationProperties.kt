package com.soul.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Properties are configured in the `application.yaml` file.
 */
@Component
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
class ApplicationProperties(
    var security: Security = Security(),
    var aws: AWS = AWS()
)

class Security(
    var authentication: Authentication = Authentication(),
    var cors: Cors = Cors(),
    var contentSecurityPolicy: String = ""
)

class Authentication(
    var secret: String = "",
    var validitySec: Long = 0,
    var validityRememberMeSec: Long = 0
)

class Cors(
    var allowedOriginPatterns: List<String> = listOf("*"),
    var allowedMethods: List<String> = listOf("*"),
    var allowedHeaders: List<String> = listOf("*"),
    var allowCredentials: Boolean = true,
)

class AWS(
    var menuBucketPrefix: String = ""
)