package com.soul.core.config

// Regex for acceptable logins
const val LOGIN_REGEX: String =
    "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$"
const val SYSTEM_ACCOUNT: String = "system"

object ApplicationProfiles {
    const val DEV = "dev"
}

object RestEndpoints {
    const val DRINKS_PATH = "/api/v1/drinks"
    const val FOOD_PATH = "/api/v1/food"
    const val AUTH_PATH = "/api"
    const val ADMIN_PATH = "/api/admin/user"
    const val IMAGES_PATH = "/api/admin/images"
}

object TableNames {
    const val DRINK = "drink"
    const val FOOD = "food"
    const val USER = "user"
}