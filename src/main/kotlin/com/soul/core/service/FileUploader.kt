package com.soul.core.service

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.CreateBucketRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.soul.core.config.ApplicationProperties
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import javax.annotation.PostConstruct

@Service
class FileUploader(
    private val s3Client: AmazonS3Client,
    properties: ApplicationProperties,
    env: Environment,
) {

    private val log = LoggerFactory.getLogger(javaClass)
    private val menuBucketName = "${properties.aws.menuBucketPrefix}-${env.getProperty("SPRING.PROFILES.ACTIVE")}"

    @PostConstruct
    fun intiBucket() {
        if (!s3Client.doesBucketExist(menuBucketName)) {
            log.debug("Bucket $menuBucketName does not exist. Creating bucket...")
            CreateBucketRequest(menuBucketName).withCannedAcl(CannedAccessControlList.PublicRead)
            s3Client.createBucket(menuBucketName)
            log.debug("Bucket $menuBucketName was created")
        }
    }

    /**
     * Uploads file to file storage
     * @return file url
     */
    fun uploadFile(file: MultipartFile): String {

        val metadata = ObjectMetadata()
        metadata.contentType = "text/plain"
        metadata.contentLength = file.size

        s3Client.putObject(
            PutObjectRequest(
                menuBucketName,
                file.originalFilename,
                file.inputStream,
                metadata
            ).withCannedAcl(CannedAccessControlList.PublicRead)
        )

        return s3Client.getResourceUrl(menuBucketName, extractFileName(file))
    }

    /**
     * Uploads files to file storage
     * @return file name to file urls map
     */
    fun uploadFiles(files: List<MultipartFile>): Map<String, String> {
        return files.filter { it.originalFilename != null }
            .associate { extractFileName(it) to uploadFile(it) }
    }

    private fun extractFileName(file: MultipartFile): String = file.originalFilename!!
}