package com.gkp.agenda.data.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.gkp.agenda.data.OfflineAgendaRepository
import com.gkp.agenda.data.alarm.AgendaItemReminderScheduler
import com.gkp.agenda.data.image.AndroidImageReader
import com.gkp.agenda.data.notification.AgendaItemReminderNotification
import com.gkp.agenda.domain.AgendaRepository
import com.gkp.agenda.domain.alarm.AlarmScheduler
import com.gkp.agenda.domain.image.ImageReader
import com.gkp.agenda.domain.notification.ReminderNotification
import com.gkp.core.database.di.dataBaseModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val agendaDataModule = module {
    includes(dataBaseModule)
    singleOf(::OfflineAgendaRepository).bind<AgendaRepository>()
    single<ImageReader> {
        AndroidImageReader(get())
    }
    singleOf(::AgendaItemReminderScheduler).bind<AlarmScheduler>()
    singleOf(::AgendaItemReminderNotification).bind<ReminderNotification>()
}