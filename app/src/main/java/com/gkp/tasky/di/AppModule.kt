package com.gkp.tasky.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.gkp.agenda.data.di.agendaDataModule
import com.gkp.agenda.presentation.di.agendaPresentationModule
import com.gkp.agenda.presentation.task.edittask.EditTaskViewModel
import com.gkp.auth.data.di.authDataModule
import com.gkp.auth.presentation.di.authPresentationModule
import com.gkp.tasky.MainViewModel
import com.gkp.tasky.TaskyApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

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