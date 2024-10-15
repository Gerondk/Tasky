package com.gkp.agenda.data.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

object ImageCompressor {

    fun compressImage(imageBytes: ByteArray, quality: Int = 50): ByteArray {
        val outputStream = ByteArrayOutputStream()
        val bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.size)
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        return outputStream.toByteArray()
    }
}