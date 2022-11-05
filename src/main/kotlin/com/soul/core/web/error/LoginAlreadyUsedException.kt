package com.soul.core.web.error

class LoginAlreadyUsedException : RuntimeException("Login is already in use!") {
    companion object {
        private const val serialVersionUID = 1L
    }
}
