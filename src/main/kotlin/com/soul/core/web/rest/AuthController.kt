package com.soul.core.web.rest

import com.fasterxml.jackson.annotation.JsonProperty
import com.soul.core.config.RestEndpoints.AUTH_PATH
import com.soul.core.domain.User
import com.soul.core.security.jwt.JWTFilter
import com.soul.core.security.jwt.TokenProvider
import com.soul.core.service.UserService
import com.soul.core.service.dto.CreateUserRequest
import com.soul.core.service.dto.LoginRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping(AUTH_PATH)
class AuthController(
    private val tokenProvider: TokenProvider,
    private var userService: UserService,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder

) {
    @PostMapping("/login")
    fun authorize(@Valid @RequestBody request: LoginRequest): ResponseEntity<JWTToken> {

        val authenticationToken = UsernamePasswordAuthenticationToken(request.login, request.password)
        val authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken)
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = tokenProvider.createToken(authentication, request.isRememberMe)
        val httpHeaders = HttpHeaders()
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer $jwt")
        return ResponseEntity(JWTToken(jwt), httpHeaders, HttpStatus.OK)
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    class JWTToken(@get:JsonProperty("id_token") var idToken: String?)

    @PostMapping("/register")
    fun register(@Valid @RequestBody user: CreateUserRequest): ResponseEntity<User> {
        val created = userService.create(user)
        return ResponseEntity.ok(created)
    }
}
