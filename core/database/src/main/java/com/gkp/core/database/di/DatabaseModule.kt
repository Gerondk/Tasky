package com.gkp.core.database.di

import androidx.room.Room
import com.gkp.agenda.domain.datasource.LocalAgendaDataSource
import com.gkp.core.database.AgendaItemsDatabase
import com.gkp.core.database.RoomLocalAgendaDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = AgendaItemsDatabase::class.java,
            name = "agenda_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        get<AgendaItemsDatabase>().agendaItemsDao
    }
    single {
        get<AgendaItemsDatabase>().deletedAgendaItemsDao

    }
    single {
        get<AgendaItemsDatabase>().createdAgendaItemsDao
    }
    single {
        get<AgendaItemsDatabase>().updatedAgendaItemsDao
    }
    singleOf(::RoomLocalAgendaDataSource).bind<LocalAgendaDataSource>()
}