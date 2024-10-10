package com.gkp.agenda.presentation.edit

import androidx.annotation.StringRes
import androidx.compose.foundation.text.input.TextFieldState
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.detail.navigation.AgendaItemType
import com.gkp.agenda.presentation.edit.util.toUiDate
import com.gkp.agenda.presentation.edit.util.toUiTime
import java.time.LocalDateTime

data class EditItemUiState(
    val id: String = "",
    val title: String = "Title",
    val description: String = "Description",
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val reminderTextId: Int = ReminderTimes.THIRTY_MINUTES_BEFORE.textId,
    val editTitleTextState: TextFieldState = TextFieldState(
        title
    ),
    val editDescriptionTextState: TextFieldState = TextFieldState(
        description
    ),
    val agendaItemType: AgendaItemType = AgendaItemType.TASK
){
    val uiDate: String
    get() = dateTime.toUiDate()
    val uiTime: String
    get() = dateTime.toUiTime()
}

enum class ReminderTimes(@StringRes val textId: Int) {
    TEN_MINUTES_BEFORE(R.string.reminder_10_minutes_before),
    THIRTY_MINUTES_BEFORE(R.string.reminder_30_minutes_before),
    ONE_HOUR_BEFORE(R.string.reminder_1_hour_before),
    SIX_HOURS_BEFORE(R.string.reminder_6_hour_before),
    ONE_DAY_BEFORE(R.string.reminder_1_day_before)
}