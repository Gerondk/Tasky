package com.gkp.core.database

import com.gkp.agenda.domain.datasource.LocalAgendaDataSource
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.core.database.mapper.toAgendaItem
import com.gkp.core.database.mapper.toAgendaItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalAgendaDataSource(
    private val agendaItemsDao: AgendaItemsDao,
) : LocalAgendaDataSource {
    override fun getAgendaItemsForDate(dateLong: Long): Flow<List<AgendaItem>> {
        return agendaItemsDao.getAllAgendaItemsForTime(dateLong).map { items ->
            items.map { it.toAgendaItem() }
        }
    }

    override suspend fun addOrSaveAgendaItem(agendaItem: AgendaItem) {
        agendaItemsDao.upsertAgendaItem(agendaItem.toAgendaItemEntity())
    }

    override suspend fun deleteAgendaItemById(id: String) {
        agendaItemsDao.deleteAgendaItemsById(id)
    }

    override suspend fun deleteAllAgendaItems() {
        agendaItemsDao.deleteAllAgendaItems()
    }

    override suspend fun getAgendaItemById(id: String): AgendaItem {
        return agendaItemsDao.getAgendaItemById(id).toAgendaItem()
    }

    override suspend fun updateAgendaItems(agendaItems: List<AgendaItem>) {
        agendaItemsDao.upsertAllAgendaItems(agendaItems.map { it.toAgendaItemEntity() })
    }
}