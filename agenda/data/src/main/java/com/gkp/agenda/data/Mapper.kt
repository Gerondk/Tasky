package com.gkp.agenda.data

import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.core.network.model.ReminderBody
import com.gkp.core.network.model.TaskBody

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