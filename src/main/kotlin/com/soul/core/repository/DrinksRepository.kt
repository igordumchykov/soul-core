package com.soul.core.repository

import com.soul.core.domain.Drinks
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DrinksRepository : MongoRepository<Drinks, String> {

}