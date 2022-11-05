package com.soul.core

import com.soul.core.service.UserService
import com.soul.core.service.dto.CreateUserRequest
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.stereotype.Component

@Component
class DatabaseInitializer(
    private val env: Environment,
    private val userService: UserService
) : ApplicationListener<ApplicationReadyEvent> {
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        val logins = env[LOGINS_ENV]?.split(",")
        val roles = env[ROLES_ENV]?.split(",")
        val passwords = env[PASSWORDS_ENV]?.split(",")
        env[EMAILS_ENV]?.split(",")
            ?.forEachIndexed { idx, i ->
                userService.upsert(
                    CreateUserRequest(
                        login = logins?.get(idx)!!,
                        password = passwords?.get(idx)!!,
                        email = i,
                        authorities = setOf(roles?.get(idx)!!)
                    )
                )
            }
    }

    companion object {
        const val EMAILS_ENV = "INIT_USER_EMAIL_LIST"
        const val LOGINS_ENV = "INIT_USER_LOGIN_LIST"
        const val ROLES_ENV = "INIT_USER_ROLE_LIST"
        const val PASSWORDS_ENV = "INIT_USER_PASSWORD_LIST"
    }
}