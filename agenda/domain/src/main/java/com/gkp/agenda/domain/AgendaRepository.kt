package com.gkp.agenda.domain

import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.core.domain.util.TaskyResult
import kotlinx.coroutines.flow.Flow

interface AgendaRepository {
    fun fetchAgendaItems(time: Long = System.currentTimeMillis()) : Flow<TaskyResult<List<AgendaItem>>>

    fun addAgendaItem(agendaItem: AgendaItem)

    fun fetchAgendaItemTask(id: String) : Flow<TaskyResult<AgendaItem.Task>>

    fun fetchAgendaItemReminder(id: String) : Flow<TaskyResult<AgendaItem.Reminder>>

    fun fetchAgendaItemEvent(id: String) : Flow<TaskyResult<AgendaItem.Event>>

    fun logout()
}