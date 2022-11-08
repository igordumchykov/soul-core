package com.soul.core.service

import com.soul.core.domain.Drinks
import com.soul.core.repository.DrinksRepository
import com.soul.core.utils.MenuUtils.Companion.attachImageUrl
import com.soul.core.utils.MenuUtils.Companion.generateSubIds
import com.soul.core.web.error.MenuNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class DrinksService(
    private val repository: DrinksRepository,
    private val fileService: ImageService
) {

    fun getAll(): Drinks =
        repository.findAll().getOrElse(0) { throw MenuNotFoundException("Drinks not found") }

    fun add(drinks: Drinks): Drinks {
        generateSubIds(drinks)
        return repository.save(drinks)
    }

    fun uploadImages(images: List<MultipartFile>): Drinks {
        val map = fileService.uploadFiles(images)
        val entity = getAll()
        attachImageUrl(entity, map)
        return repository.save(entity)
    }

    fun deleteAll() = repository.deleteAll()
}