package com.gkp.agenda.domain.datasource

import com.gkp.agenda.domain.model.AgendaItem
import kotlinx.coroutines.flow.Flow

interface LocalAgendaDataSource {
    fun getAgendaItemsForDate(dateLong: Long): Flow<List<AgendaItem>>
    suspend fun addOrSaveAgendaItem(agendaItem: AgendaItem)
    suspend fun deleteAgendaItemById(id: String)
    suspend fun deleteAllAgendaItems()
    suspend fun updateAgendaItem(agendaItem: AgendaItem)
    suspend fun getAgendaItemById(id: String): AgendaItem
    suspend fun updateAgendaItems(agendaItems: List<AgendaItem>)
}