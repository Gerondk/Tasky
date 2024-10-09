package com.gkp.agenda.presentation.detail

import com.gkp.agenda.presentation.detail.navigation.AgendaItemType

data class AgendaItemDetailUiState(
    val agendaItemType: AgendaItemType = AgendaItemType.TASK,
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val time: String = "",
    val reminderTime: String = "",
)
