package com.soul.core.web.rest

import com.soul.core.config.RestEndpoints.FOOD_PATH
import com.soul.core.domain.Drinks
import com.soul.core.domain.Food
import com.soul.core.security.ADMIN
import com.soul.core.service.FoodService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(FOOD_PATH)
class FoodController(
    private val service: FoodService,
) {

    @GetMapping
    fun getAll(): ResponseEntity<Food> {
        return ResponseEntity.ok(service.getAll())
    }

    @PreAuthorize("hasAuthority(\"$ADMIN\")")
    @DeleteMapping
    fun deleteAll(): ResponseEntity<Drinks> {
        service.deleteAll()
        return ResponseEntity.ok().build()
    }

    @PreAuthorize("hasAuthority(\"$ADMIN\")")
    @PostMapping
    fun add(@RequestBody payload: Food): ResponseEntity<Food> {
        return ResponseEntity.ok(service.add(payload))
    }

    @PreAuthorize("hasAuthority(\"$ADMIN\")")
    @PostMapping("/images")
    fun uploadImages(@RequestParam("img") images: List<MultipartFile>): ResponseEntity<Food> {
        return ResponseEntity.ok(service.uploadImages(images))
    }
}