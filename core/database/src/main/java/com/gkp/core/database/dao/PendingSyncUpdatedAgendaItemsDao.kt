package com.gkp.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gkp.core.database.entity.UpdatedAgendaItemEntity

@Dao
interface PendingSyncUpdatedAgendaItemsDao {

    @Query("DELETE FROM updatedagendaitementity WHERE id = :id")
    suspend fun deleteById(id: String)

    @Upsert
    suspend fun upsertUpdatedItem(item: UpdatedAgendaItemEntity)

    @Query("SELECT * FROM updatedagendaitementity WHERE userId = :userId")
    suspend fun getUpdatedItemsByUserId(userId: String): List<UpdatedAgendaItemEntity>

}