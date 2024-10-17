package com.gkp.agenda.domain.model

import com.gkp.agenda.domain.util.formattedDateTime

sealed interface AgendaItem {
    val id: String
    val title: String
    val description: String?
    val time: Long
    val remindAt: Long
    val formattedDateTime: String
        get() = time.formattedDateTime()

    data class Task(
        override val id: String,
        override val title: String,
        override val description: String? = null,
        override val time: Long,
        override val remindAt: Long,
        val isDone: Boolean = false,
    ) : AgendaItem

    data class Reminder(
        override val id: String,
        override val title: String,
        override val description: String? = null,
        override val time: Long,
        override val remindAt: Long,
    ) : AgendaItem

    data class Event(
        override val id: String,
        override val title: String,
        override val description: String? = null,
        override val time: Long, // from for the event start time
        override val remindAt: Long,
        val to: Long, // to for the event end time
        val attendeesIds: List<String>,
        val photos: List<ByteArray>,
    ) : AgendaItem
}

enum class AgendaItemType {
    TASK,
    REMINDER,
    EVENT
}