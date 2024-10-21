package com.gkp.core.database.mapper

import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.core.database.entity.AgendaItemEntity

fun AgendaItemEntity.toAgendaItem(): AgendaItem {
    return when {
        isDone != null -> {
            AgendaItem.Task(
                id = id,
                title = title,
                description = description,
                time = time,
                remindAt = remindAt,
                isDone = isDone
            )
        }
        to != null -> {
            AgendaItem.Event(
                id = id,
                title = title,
                description = description,
                time = time,
                remindAt = remindAt,
                to = to,
                attendeesIds = emptyList(),
                photos = emptyList()
            )
        }
        else -> {
            AgendaItem.Reminder(
                id = id,
                title = title,
                description = description,
                time = time,
                remindAt = remindAt
            )
        }
    }
}

fun AgendaItem.toAgendaItemEntity(): AgendaItemEntity {
    return when (this) {
        is AgendaItem.Event -> {
            AgendaItemEntity(
                id = id,
                title = title,
                description = description,
                time = time,
                remindAt = remindAt,
                to = to,
            )
        }
        is AgendaItem.Reminder -> {
             AgendaItemEntity(
                id = id,
                title = title,
                description = description,
                time = time,
                remindAt = remindAt,
            )
        }
        is AgendaItem.Task -> {
            AgendaItemEntity(
                id = id,
                title = title,
                description = description,
                time = time,
                remindAt = remindAt,
                isDone = isDone
            )
        }
    }
}