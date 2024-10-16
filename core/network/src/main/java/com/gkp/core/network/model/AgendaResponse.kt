package com.gkp.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class AgendaResponse(
    val events: List<EventResponse>,
    val tasks: List<TaskResponse>,
    val reminders: List<ReminderResponse>
)

@Serializable
data class TaskResponse(
    val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    val remindAt: Long,
    val isDone: Boolean
)

@Serializable
data class ReminderResponse(
    val id: String,
    val title: String,
    val description: String?,
    val time: Long,
    val remindAt: Long
)

@Serializable
data class EventResponse(
    val id: String,
    val title: String,
    val description: String?,
    val from: Long,
    val to: Long,
    val remindAt: Long,
    val host: String,
    val isUserEventCreator: Boolean,
    val attendees: List<EventAttendeeResponse>,
    val photos: List<EventPhotoResponse>,
)

@Serializable
data class EventAttendeeResponse(
    val email: String,
    val fullName: String,
    val userId: String,
    val eventId: String,
    val isGoing: Boolean,
    val remindAt: Long,
    val createdAt: String,
)

@Serializable
data class EventPhotoResponse(
    val key: String,
    val url: String,
)