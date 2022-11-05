package com.soul.core.web.error

class UserNotFoundException : RuntimeException("User not found") {
    companion object {
        private const val serialVersionUID = 1L
    }
}
