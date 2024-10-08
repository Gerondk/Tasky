package com.gkp.agenda.domain.model

import com.gkp.agenda.domain.util.formattedDateTime
import com.gkp.agenda.domain.util.toLocalDateTime

sealed interface AgendaItem {
    val id: String
    val title: String
    val description: String?
    val time: Long
    val remindAt: Long
    val formattedDateTime: String
        get() = time.formattedDateTime()

    data class Task(
        override val id: String = "",
        override val title: String = "",
        override val description: String? = null,
        override val time: Long = 0L,
        override val remindAt: Long = 0L,
        val isDone: Boolean = false,
    ) : AgendaItem

    data class Reminder(
        override val id: String = "",
        override val title: String = "",
        override val description: String? = null,
        override val time: Long = 0L,
        override val remindAt: Long = 0L,
    ) : AgendaItem
}