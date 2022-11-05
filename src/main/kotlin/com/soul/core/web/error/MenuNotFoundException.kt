package com.soul.core.web.error

class MenuNotFoundException(msg: String) : RuntimeException(msg) {

    companion object {
        private const val serialVersionUID = 1L
    }
}
