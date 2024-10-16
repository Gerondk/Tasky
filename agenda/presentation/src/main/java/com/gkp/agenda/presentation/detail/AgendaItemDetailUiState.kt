package com.gkp.agenda.presentation.detail

import com.gkp.agenda.domain.model.AgendaItemType


data class AgendaItemDetailUiState(
    val agendaItemType: AgendaItemType = AgendaItemType.TASK,
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val time: String = "",
    val reminderTime: String = "",
)
