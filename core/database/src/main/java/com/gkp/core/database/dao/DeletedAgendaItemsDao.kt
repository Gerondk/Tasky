package com.gkp.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gkp.core.database.entity.DeletedAgendaItemEntity

@Dao
interface DeletedAgendaItemsDao {

    @Query("SELECT * FROM deletedagendaitementity WHERE userId = :userId")
    suspend fun getAllDeletedAgendaItemsForUser(userId: String): List<DeletedAgendaItemEntity>

    @Query("DELETE FROM deletedagendaitementity WHERE id = :id")
    suspend fun deleteForId(id: String)

    @Query("DELETE FROM deletedagendaitementity")
    suspend fun deleteAll()

    @Upsert
    suspend fun upsert(deletedAgendaItemEntity: DeletedAgendaItemEntity)

}