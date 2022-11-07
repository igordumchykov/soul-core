package com.soul.core.service.mapper

import com.amazonaws.services.s3.model.S3ObjectSummary
import com.soul.core.service.dto.ImageDto
import org.springframework.stereotype.Service

@Service
class ImageMapper {

    fun toDtoList(images: MutableList<S3ObjectSummary>) = images.mapTo(mutableListOf()) { toDto(it) }

    fun toDto(image: S3ObjectSummary) = ImageDto(
        bucketName = image.bucketName,
        key = image.key,
        size = image.size,
        lastModified = image.lastModified
    )
}