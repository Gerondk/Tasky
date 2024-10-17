package com.gkp.agenda.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.gkp.agenda.domain.AgendaRepository
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.domain.model.AgendaItemType
import com.gkp.agenda.presentation.detail.navigation.AgendaItemDetailScreenRoute
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AgendaItemDetailViewModel(
    savedStateHandle: SavedStateHandle,
    agendaRepository: AgendaRepository,
) : ViewModel() {

    var uiState by mutableStateOf(AgendaItemDetailUiState())
        private set

    init {
        val detailScreenRoute = savedStateHandle.toRoute<AgendaItemDetailScreenRoute>()
        val itemId = detailScreenRoute.agendaItemId
        val itemType = detailScreenRoute.agendaItemType
        when (itemType) {
            AgendaItemType.TASK -> {
                agendaRepository.fetchAgendaItemTask(itemId).onEach { taskyResult ->
                    val agendaItem = taskyResult.getDataOrNull()
                    setUiState(agendaItem, itemType)
                }
            }

            AgendaItemType.REMINDER -> {
                agendaRepository.fetchAgendaItemReminder(itemId).onEach { taskyResult ->
                    val agendaItem = taskyResult.getDataOrNull()
                    setUiState(agendaItem, itemType)
                }
            }

            AgendaItemType.EVENT -> {
                agendaRepository.fetchAgendaItemEvent(itemId).onEach { taskyResult ->
                    val agendaItem = taskyResult.getDataOrNull()
                    setUiState(agendaItem, itemType)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun setUiState(
        agendaItem: AgendaItem?,
        itemType: AgendaItemType,
    ) {
        agendaItem?.let {
            uiState = uiState.copy(
                agendaItemType = itemType,
                id = it.id,
                title = it.title,
                description = it.description ?: "",
                time = it.time.toString(),
                reminderTime = it.remindAt.toString()
            )
        }
    }

}

