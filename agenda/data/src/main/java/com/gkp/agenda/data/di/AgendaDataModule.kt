package com.gkp.agenda.data.di

import com.gkp.agenda.data.OfflineAgendaRepository
import com.gkp.agenda.data.image.AndroidImageReader
import com.gkp.agenda.domain.AgendaRepository
import com.gkp.agenda.domain.image.ImageReader
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val agendaDataModule = module {
    singleOf(::OfflineAgendaRepository).bind<AgendaRepository>()
    single<ImageReader> {
        AndroidImageReader(get())
    }
}