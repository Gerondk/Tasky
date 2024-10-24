package com.gkp.tasky.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.gkp.agenda.data.di.agendaDataModule
import com.gkp.agenda.presentation.di.agendaPresentationModule
import com.gkp.auth.data.di.authDataModule
import com.gkp.auth.presentation.di.authPresentationModule
import com.gkp.tasky.MainViewModel
import com.gkp.tasky.TaskyApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val appModule = module {
    includes(
        authPresentationModule,
        authDataModule,
        agendaPresentationModule,
        agendaDataModule
    )
    viewModelOf(::MainViewModel)
    single {
        (androidContext() as TaskyApplication).applicationScope
    }
}