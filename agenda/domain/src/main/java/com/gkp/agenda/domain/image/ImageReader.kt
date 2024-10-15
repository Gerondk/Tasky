package com.gkp.agenda.domain.image


interface ImageReader {
    suspend fun readImagesFromStringUri(stringUris: List<String>): List<ImageInfo>
}

data class ImageInfo(
    val type: String,
    val uri: String,
    val byteArray: ByteArray
)