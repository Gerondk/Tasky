package com.gkp.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gkp.core.database.entity.CreatedAgendaItemEntity

@Dao
interface PendingSyncCreatedAgendaItemsDao {

    @Query("DELETE FROM CreatedAgendaItemEntity WHERE id = :id")
    suspend fun deleteById(id: String)

    @Upsert
    suspend fun upsert(item: CreatedAgendaItemEntity)

    @Query("SELECT * FROM CreatedAgendaItemEntity WHERE userId = :userId")
    suspend fun getCreatedAgendaItemsByUserId(userId: String): List<CreatedAgendaItemEntity>

}