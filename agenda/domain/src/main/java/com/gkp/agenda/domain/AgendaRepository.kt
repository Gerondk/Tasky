package com.gkp.agenda.domain

import com.gkp.agenda.domain.model.AgendaItem
import kotlinx.coroutines.flow.Flow

interface AgendaRepository {
    fun getAgendaItemsForDate(dateLong: Long): Flow<List<AgendaItem>>

    suspend fun fetchAgendaItems()

    suspend fun addAgendaItem(agendaItem: AgendaItem)

    suspend fun updateAgendaItem(agendaItem: AgendaItem)

    suspend fun deleteAgendaItem(agendaItem: AgendaItem)

    suspend fun getAgendaItemForId(id: String): AgendaItem

    suspend fun pushOfflineAgendaItems()

    suspend fun periodicalSyncAgendaItems()

    suspend fun logout()
}