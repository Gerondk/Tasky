package com.gkp.agenda.presentation.detail

import com.gkp.agenda.domain.model.AgendaItemType
import com.gkp.agenda.presentation.edit.ReminderTimes
import com.gkp.agenda.presentation.edit.util.toUiDate
import com.gkp.agenda.presentation.edit.util.toUiTime
import java.time.LocalDateTime


data class AgendaItemDetailUiState(
    val agendaItemType: AgendaItemType = AgendaItemType.TASK,
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val toDateTime: LocalDateTime = LocalDateTime.now(),
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val reminderTextId: Int = ReminderTimes.THIRTY_MINUTES_BEFORE.textId,
){
    val uiDate: String
        get() = dateTime.toUiDate()
    val uiTime: String
        get() = dateTime.toUiTime()
    val uiToDateTime: String
        get() = toDateTime.toUiDate()
    val uiToTime: String
        get() = toDateTime.toUiTime()
}
