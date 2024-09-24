package com.gkp.agenda.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.agenda.domain.AgendaRepository
import kotlinx.coroutines.launch

class AgendaViewModel(
    private val agendaRepository: AgendaRepository,
) : ViewModel() {

    fun logout() {
        agendaRepository.logout()
    }
}