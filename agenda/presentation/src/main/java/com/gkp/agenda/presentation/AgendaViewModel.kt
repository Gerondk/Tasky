package com.gkp.agenda.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.agenda.domain.AgendaRepository
import com.gkp.auth.domain.session.SessionStorage
import kotlinx.coroutines.launch
import java.time.LocalDate

class AgendaViewModel(
    private val agendaRepository: AgendaRepository,
    private val sessionStorage: SessionStorage,
) : ViewModel() {

    init {
        setFullName()
    }

    var agendaUiState by mutableStateOf(AgendaUiState())
        private set

    fun onDateSelected(selectedDate: LocalDate) {
        agendaUiState = agendaUiState.copy(
            selectedDate = selectedDate
        )
    }

    fun logout() {
        agendaRepository.logout()
    }

    private fun setFullName() {
        viewModelScope.launch {
            val authInfo = sessionStorage.getAuthInfo()
            agendaUiState = agendaUiState.copy(
                fullName = authInfo.fullName
            )
        }
    }
}