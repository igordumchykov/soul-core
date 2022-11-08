package com.soul.core.service

import com.soul.core.domain.Food
import com.soul.core.repository.FoodRepository
import com.soul.core.utils.MenuUtils.Companion.attachImageUrl
import com.soul.core.utils.MenuUtils.Companion.generateSubIds
import com.soul.core.web.error.MenuNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class FoodService(
    private val repository: FoodRepository,
    private val fileService: ImageService
) {

    fun getAll(): Food =
        repository.findAll().getOrElse(0) { throw MenuNotFoundException("Food not found") }

    fun add(food: Food): Food {
        generateSubIds(food)
        return repository.save(food)
    }

    fun uploadImages(images: List<MultipartFile>): Food {
        val map = fileService.uploadFiles(images)
        val entity = getAll()
        attachImageUrl(entity, map)
        return repository.save(entity)
    }

    fun deleteAll() = repository.deleteAll()

}