package com.soul.core.web.rest

import com.soul.core.config.RestEndpoints.DRINKS_PATH
import com.soul.core.domain.Drinks
import com.soul.core.security.ADMIN
import com.soul.core.service.DrinksService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(DRINKS_PATH)
class DrinksController(
    private val service: DrinksService,
) {

    @GetMapping
    fun getAll(): ResponseEntity<Drinks> {
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
    fun add(@RequestBody payload: Drinks): ResponseEntity<Drinks> {
        return ResponseEntity.ok(service.add(payload))
    }

    @PreAuthorize("hasAuthority(\"$ADMIN\")")
    @PostMapping("/images")
    fun uploadImages(@RequestParam("img") images: List<MultipartFile>): ResponseEntity<Drinks> {
        return ResponseEntity.ok(service.uploadImages(images))
    }
}