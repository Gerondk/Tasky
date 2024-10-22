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
import com.gkp.agenda.domain.util.toLocalDateTime
import com.gkp.agenda.presentation.detail.navigation.AgendaItemDetailScreenRoute
import com.gkp.agenda.presentation.edit.util.reminderLongToReminderTimeTextId
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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
        viewModelScope.launch {
            val agendaItem = agendaRepository.getAgendaItemForId(itemId)
            setUiState(agendaItem, itemType)
        }
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
                dateTime = it.time.toLocalDateTime(),
                reminderTextId = reminderLongToReminderTimeTextId(
                    it.remindAt,
                    it.time
                ),
                toDateTime = if (it is AgendaItem.Event) it.to.toLocalDateTime() else it.time.toLocalDateTime()
            )
        }
    }

}

