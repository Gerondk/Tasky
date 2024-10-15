package com.gkp.agenda.data.image

import android.content.Context
import android.net.Uri
import com.gkp.agenda.domain.image.ImageInfo
import com.gkp.agenda.domain.image.ImageReader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class AndroidImageReader(
    private val context: Context,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ImageReader {
    override suspend fun readImagesFromStringUri(
        stringUris: List<String>,
    ): List<ImageInfo> {
        return withContext(dispatcher) {
            stringUris.map { uriString ->
                async {
                    val content = context.contentResolver.openInputStream(Uri.parse(uriString))?.use {
                        it.readBytes()
                    } ?: byteArrayOf()
                    val type = context.contentResolver.getType(Uri.parse(uriString)) ?: ""
                    val compressedImage = ImageCompressor.compressImage(content)
                    ImageInfo(type, uriString, compressedImage)
                }

            }.awaitAll()
        }
    }
}