package com.gkp.agenda.presentation

import androidx.lifecycle.ViewModel
import com.gkp.agenda.domain.AgendaRepository

class AgendaViewModel(
    private val agendaRepository: AgendaRepository,
) : ViewModel() {

    fun logout() {
        agendaRepository.logout()
    }
}