package com.gkp.agenda.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.agenda.domain.AgendaRepository
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.presentation.util.toMillis
import com.gkp.auth.domain.session.SessionStorage
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate

@Suppress("OPT_IN_USAGE")
class AgendaViewModel(
    private val agendaRepository: AgendaRepository,
    private val sessionStorage: SessionStorage,
) : ViewModel() {

    var agendaUiState by mutableStateOf(AgendaUiState())
        private set

    init {
        setFullName()
        snapshotFlow {
            agendaUiState.selectedDate
        }
            .distinctUntilChanged()
            .flatMapLatest {
                agendaRepository.getAgendaItemsForDate(it.toMillis())
            }
            .onEach { items ->
                val agendaItems = items.sortedBy { it.time }
                agendaUiState = agendaUiState.copy(
                    agendaItems = agendaItems
                )
            }.launchIn(viewModelScope)

        viewModelScope.launch {
            agendaRepository.fetchAgendaItems()
        }
    }


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

    fun deleteAgendaItem(agendaItem: AgendaItem) {
        viewModelScope.launch {
            agendaRepository.deleteAgendaItem(agendaItem)
        }
    }

    fun onTaskTitleClick(id: String) {
        viewModelScope.launch {
            val item = agendaUiState.agendaItems.find { it.id == id } as? AgendaItem.Task
            val toSaveItem = item?.copy(isDone = item.isDone.not())
            toSaveItem?.let {
                agendaRepository.updateAgendaItem(it)
            }
        }
    }
}