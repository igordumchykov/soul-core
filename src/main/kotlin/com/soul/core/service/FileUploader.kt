package com.soul.core.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class FileUploader {

    /**
     * Uploads file to file storage
     * @return file url
     */
    fun uploadFile(file: MultipartFile): String {
        //todo: implement file upload to S3/Cloudinary

        //stub:
        return "${file.originalFilename!!.split(".")[0]}.someUrl"
    }

    /**
     * Uploads files to file storage
     * @return file name to file urls map
     */
    fun uploadFiles(files: List<MultipartFile>): Map<String, String> {
        //todo: implement file upload to S3/Cloudinary

        //stub:
        return files.filter { it.originalFilename != null }
            .associate { extractFileName(it) to "${extractFileName(it).split(".")[0]}.someUrl" }
    }

    private fun extractFileName(file: MultipartFile): String = file.originalFilename!!
}