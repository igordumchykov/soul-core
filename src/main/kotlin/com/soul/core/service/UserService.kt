package com.soul.core.service

import com.soul.core.domain.User
import com.soul.core.repository.UserRepository
import com.soul.core.service.dto.CreateUserRequest
import com.soul.core.service.dto.UpdateUserRequest
import com.soul.core.web.error.EmailAlreadyUsedException
import com.soul.core.web.error.LoginAlreadyUsedException
import com.soul.core.web.error.UserNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,

    ) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun create(userDTO: CreateUserRequest): User {
        userRepository.findOneByLogin(userDTO.login).ifPresent {
            throw LoginAlreadyUsedException()
        }
        userRepository.findOneByEmailIgnoreCase(userDTO.email).ifPresent {
            throw EmailAlreadyUsedException()
        }

        val user = User(
            login = userDTO.login,
            email = userDTO.email,
            firstName = userDTO.firstName,
            lastName = userDTO.lastName,
            password = passwordEncoder.encode(userDTO.password),
            authorities = userDTO.authorities
        )
        userRepository.save(user)
        log.debug("Created Information for User: $user")
        return user
    }

    fun upsert(request: CreateUserRequest): User {
        var upserted: User? = null
        userRepository.findOneByLogin(request.login).ifPresentOrElse({
            val user = User(
                id = it.id,
                firstName = request.firstName,
                lastName = request.lastName,
                authorities = request.authorities,
                login = it.login,
                password = it.password
            )
            upserted = update(user)
        }, {
            upserted = create(request)
        })
        return upserted!!
    }

    fun update(id: String, request: UpdateUserRequest): User {
        var updated: User? = null
        userRepository.findById(id).ifPresentOrElse({
            val user = User(
                id = it.id,
                firstName = request.firstName,
                lastName = request.lastName,
                authorities = request.authorities,
                login = it.login,
                password = it.password
            )
            updated = update(user)
        }, {
            throw UserNotFoundException()
        })
        return updated!!
    }

    fun update(user: User): User {
        userRepository.save(user)
        log.debug("Updated Information for User: $user")
        return user
    }

    fun delete(id: String) {
        userRepository.deleteById(id)
    }

    fun get(id: String): User {
        return userRepository.findById(id).orElseThrow { UserNotFoundException() }
    }

    fun getAll(): List<User> {
        return userRepository.findAll()
    }
}