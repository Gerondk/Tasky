package com.gkp.agenda.data

import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.core.network.model.AgendaResponse
import com.gkp.core.network.model.EventBody
import com.gkp.core.network.model.ReminderBody
import com.gkp.core.network.model.ReminderResponse
import com.gkp.core.network.model.TaskBody
import com.gkp.core.network.model.TaskResponse

fun AgendaItem.Task.toTaskBody() = TaskBody(
    id = id,
    title = title,
    description = description,
    time = time,
    remindAt = remindAt,
    isDone = isDone
)

fun AgendaItem.Reminder.toReminderBody() = ReminderBody(
    id = id,
    title = title,
    description = description,
    time = time,
    remindAt = remindAt
)

fun TaskResponse.toAgendaTaskItem() = AgendaItem.Task(
    id = id,
    title = title,
    description = description,
    time = time,
    remindAt = remindAt,
    isDone = isDone
)

fun ReminderResponse.toAgendaReminderItem() = AgendaItem.Reminder(
    id = id,
    title = title,
    description = description,
    time = time,
    remindAt = remindAt
)
fun AgendaResponse.toAgendaItems() =
    tasks.map { it.toAgendaTaskItem() } + reminders.map { it.toAgendaReminderItem() }

fun AgendaItem.Event.toEventBody() = EventBody(
    id = id,
    title = title,
    description = description!!,
    from = time,
    to = to,
    remindAt = remindAt,
    attendeeIds = attendeesIds
)
