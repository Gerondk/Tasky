package com.gkp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gkp.core.database.entity.AgendaItemEntity

@Database(
    entities = [AgendaItemEntity::class],
    exportSchema = true,
    version = 1
)
abstract class AgendaItemsDatabase : RoomDatabase() {
    abstract val agendaItemsDao: AgendaItemsDao
}