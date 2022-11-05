package com.soul.core.repository

import com.soul.core.domain.Food
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodRepository : MongoRepository<Food, String> {

}