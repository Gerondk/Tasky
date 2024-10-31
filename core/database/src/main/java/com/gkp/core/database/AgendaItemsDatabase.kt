package com.gkp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gkp.core.database.dao.AgendaItemsDao
import com.gkp.core.database.dao.PendingSyncCreatedAgendaItemsDao
import com.gkp.core.database.dao.PendingSyncDeletedAgendaItemsDao
import com.gkp.core.database.dao.PendingSyncUpdatedAgendaItemsDao
import com.gkp.core.database.entity.AgendaItemEntity
import com.gkp.core.database.entity.CreatedAgendaItemEntity
import com.gkp.core.database.entity.DeletedAgendaItemEntity
import com.gkp.core.database.entity.UpdatedAgendaItemEntity

@Database(
    entities = [
        AgendaItemEntity::class,
        DeletedAgendaItemEntity::class,
        CreatedAgendaItemEntity::class,
        UpdatedAgendaItemEntity::class
    ],
    exportSchema = true,
    version = 1
)
abstract class AgendaItemsDatabase : RoomDatabase() {
    abstract val agendaItemsDao: AgendaItemsDao
    abstract val deletedAgendaItemsDao: PendingSyncDeletedAgendaItemsDao
    abstract val createdAgendaItemsDao: PendingSyncCreatedAgendaItemsDao
    abstract val updatedAgendaItemsDao: PendingSyncUpdatedAgendaItemsDao
}