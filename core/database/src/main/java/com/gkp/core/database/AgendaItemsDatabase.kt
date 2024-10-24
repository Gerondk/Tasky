package com.gkp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gkp.core.database.dao.AgendaItemsDao
import com.gkp.core.database.dao.CreatedAgendaItemsDao
import com.gkp.core.database.dao.DeletedAgendaItemsDao
import com.gkp.core.database.dao.UpdatedAgendaItemsDao
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
    abstract val deletedAgendaItemsDao: DeletedAgendaItemsDao
    abstract val createdAgendaItemsDao: CreatedAgendaItemsDao
    abstract val updatedAgendaItemsDao: UpdatedAgendaItemsDao
}