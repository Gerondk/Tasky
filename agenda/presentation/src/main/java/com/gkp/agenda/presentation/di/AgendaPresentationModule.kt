package com.gkp.agenda.presentation.di

import com.gkp.agenda.presentation.home.AgendaViewModel
import com.gkp.agenda.presentation.edit.EditAgendaItemViewModel
import com.gkp.agenda.presentation.detail.AgendaItemDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val agendaPresentationModule = module {
    viewModelOf(::AgendaViewModel)
    viewModelOf(::EditAgendaItemViewModel)
    viewModelOf(::AgendaItemDetailViewModel)
}