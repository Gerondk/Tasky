package com.gkp.core.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.UUID

@Serializable
data class EventBody(
    val id: String,
    val title: String,
    val description: String,
    val from: Long,
    val to: Long,
    val remindAt: Long,
    val attendeeIds: List<String>
)

fun buildEventBodyPart(eventBody: EventBody): MultipartBody.Part {
    return MultipartBody.Part.createFormData(
        name = "create_event_request",
        value = Json.encodeToString(eventBody)
    )
}

fun buildPhotosPart(photos: List<ByteArray>): List<MultipartBody.Part> {
    val photosList = mutableListOf<MultipartBody.Part>()
    photos.forEachIndexed { index, photo ->
        val part = MultipartBody.Part.createFormData(
            "photo$index",
            "${UUID.randomUUID()}.jpeg",
            photo.toRequestBody()
        )
        photosList.add(part)
    }
    return photosList
}

