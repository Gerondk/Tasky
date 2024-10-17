package com.gkp.agenda.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.agenda.domain.AgendaRepository
import com.gkp.agenda.domain.image.ImageReader
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
                agendaRepository.fetchAgendaItems(it.toMillis())
            }
            .onEach { taskResult ->
                val agendaItems = taskResult.getDataOrNull()?.sortedBy { it.time } ?: emptyList()
                agendaUiState = agendaUiState.copy(
                    agendaItems = agendaItems
                )
            }.launchIn(viewModelScope)
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
        agendaRepository.deleteAgendaItem(agendaItem)
    }

    fun onTaskTitleClick(id: String) {
        agendaUiState.agendaItems.indexOfFirst { it.id == id }.let {
            if (it != -1) {
                var item = agendaUiState.agendaItems[it]
                val agendaItems = agendaUiState.agendaItems - item
                item = if (item is AgendaItem.Task) {
                    item.copy(isDone = !item.isDone)
                } else {
                    item

                }
                agendaUiState = agendaUiState.copy(
                    agendaItems = agendaItems + item
                )
            }

        }

    }
}