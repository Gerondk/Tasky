package com.gkp.agenda.domain.sync

import com.gkp.agenda.domain.model.AgendaItemType

interface SyncAgendaItems {
    suspend fun syncDeletedAgendaItem(agendaItemId: String, agendaItemType: AgendaItemType)
    suspend fun syncCreatedAgendaItem(agendaItemId: String)
    suspend fun syncUpdatedAgendaItem(agendaItemId: String)
    suspend fun syncFullAgenda()
    suspend fun cancelWorkers()
}