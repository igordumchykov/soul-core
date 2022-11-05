package com.soul.core.security

import com.soul.core.domain.User
import com.soul.core.repository.UserRepository
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator
import org.slf4j.LoggerFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
class DomainUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun loadUserByUsername(login: String): UserDetails {
        log.debug("Authenticating $login")

        if (EmailValidator().isValid(login, null)) {
            return userRepository.findOneByEmailIgnoreCase(login)
                .map { createSpringSecurityUser(it) }
                .orElseThrow { UsernameNotFoundException("User with email $login was not found in the database") }
        }

        val lowercaseLogin = login.toLowerCase(Locale.ENGLISH)
        return userRepository.findOneByLogin(lowercaseLogin)
            .map { createSpringSecurityUser(it) }
            .orElseThrow { UsernameNotFoundException("User $lowercaseLogin was not found in the database") }
    }

    private fun createSpringSecurityUser(user: User)
            : org.springframework.security.core.userdetails.User {
        val grantedAuthorities = user.authorities?.map { SimpleGrantedAuthority(it) }
        return org.springframework.security.core.userdetails.User(
            user.login!!,
            user.password!!,
            grantedAuthorities
        )
    }
}
