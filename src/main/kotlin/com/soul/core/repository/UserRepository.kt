package com.soul.core.repository

import com.soul.core.domain.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findOneByEmailIgnoreCase(email: String?): Optional<User>
    fun findOneByLogin(login: String): Optional<User>
}