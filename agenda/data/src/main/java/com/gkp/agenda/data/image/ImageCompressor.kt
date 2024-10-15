package com.gkp.agenda.data.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

object ImageCompressor {

    suspend fun compressImage(imageBytes: ByteArray, quality: Int = 50): ByteArray {
        return withContext(Dispatchers.IO){
            val outputStream = ByteArrayOutputStream()
            val bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.size)
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            outputStream.toByteArray()
        }
    }
}