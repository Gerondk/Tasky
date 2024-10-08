package com.gkp.agenda.domain

import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.core.domain.util.TaskyResult
import kotlinx.coroutines.flow.Flow

interface AgendaRepository {
    fun fetchAgendaItems(time: Long = System.currentTimeMillis()) : Flow<TaskyResult<List<AgendaItem>>>
    fun addAgendaItem(agendaItem: AgendaItem)
    fun logout()
}