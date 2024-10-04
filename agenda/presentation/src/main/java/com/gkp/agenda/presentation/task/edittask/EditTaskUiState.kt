package com.gkp.agenda.presentation.task.edittask

import androidx.annotation.StringRes
import androidx.compose.foundation.text.input.TextFieldState
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.task.edittask.util.toUiDate
import com.gkp.agenda.presentation.task.edittask.util.toUiTime
import java.time.LocalDateTime

data class EditTaskUiState(
    val taskTitle: String = "Task",
    val taskDescription: String = "Description",
    val taskDateTime: LocalDateTime = LocalDateTime.now(),
    val taskReminderTextId: Int = ReminderTimes.THIRTY_MINUTES_BEFORE.textId,
    val editTitleTextState: TextFieldState = TextFieldState(
        taskTitle
    ),
    val editDescriptionTextState: TextFieldState = TextFieldState(
        taskDescription
    ),
){
    val uiDate: String
    get() = taskDateTime.toUiDate()
    val uiTime: String
    get() = taskDateTime.toUiTime()
}

enum class ReminderTimes(@StringRes val textId: Int) {
    TEN_MINUTES_BEFORE(R.string.reminder_10_minutes_before),
    THIRTY_MINUTES_BEFORE(R.string.reminder_30_minutes_before),
    ONE_HOUR_BEFORE(R.string.reminder_1_hour_before),
    SIX_HOURS_BEFORE(R.string.reminder_6_hour_before),
    ONE_DAY_BEFORE(R.string.reminder_1_day_before)
}