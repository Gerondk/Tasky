package com.gkp.agenda.data

import com.gkp.agenda.domain.model.Task
import com.gkp.core.network.model.TaskBody

fun Task.toTaskBody() = TaskBody(
    id = id,
    title = title,
    description = description,
    time = time,
    remindAt = remindAt,
    isDone = isDone
)