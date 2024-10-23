package com.gkp.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gkp.core.database.entity.AgendaItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaItemsDao {

    @Query("SELECT * FROM agendaitementity WHERE date = :date ORDER BY time ASC")
    fun getAllAgendaItemsForTime(date: Long): Flow<List<AgendaItemEntity>>

    @Query("SELECT * FROM agendaitementity WHERE id = :id")
     suspend fun getAgendaItemById(id: String): AgendaItemEntity

    @Upsert
    suspend fun upsertAgendaItem(
        agendaItemEntity: AgendaItemEntity
    )

    @Upsert
    suspend fun upsertAllAgendaItems(
        agendaItems: List<AgendaItemEntity>
    )

    @Query("DELETE FROM agendaitementity WHERE id = :id")
    suspend fun deleteAgendaItemsById(id: String)

    @Query("DELETE FROM agendaitementity")
    suspend fun deleteAllAgendaItems()



}