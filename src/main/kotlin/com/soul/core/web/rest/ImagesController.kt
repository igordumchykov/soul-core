package com.soul.core.web.rest

import com.soul.core.config.RestEndpoints.IMAGES_PATH
import com.soul.core.service.ImageService
import com.soul.core.service.dto.ImageDto
import com.soul.core.service.dto.RenameImageRequest
import com.soul.core.service.mapper.ImageMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(IMAGES_PATH)
class ImagesController(
    private var imageService: ImageService,
    private var imageMapper: ImageMapper
) {

    @GetMapping("/all")
    fun listImages(): ResponseEntity<List<ImageDto>> {
        val images = imageService.listImages().let { imageMapper.toDtoList(it!!) }
        return ResponseEntity.ok(images)
    }

    @PostMapping
    fun rename(@Valid @RequestBody request: RenameImageRequest): ResponseEntity<Void> {
        imageService.renameImage(request.oldName!!, request.newName!!)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping
    fun remove(@Valid @RequestBody image: ImageDto): ResponseEntity<Void> {
        imageService.removeImage(image.key!!)
        return ResponseEntity.ok().build()
    }
}