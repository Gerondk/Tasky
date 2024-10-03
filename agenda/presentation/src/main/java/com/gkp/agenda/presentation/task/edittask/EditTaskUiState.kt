package com.gkp.agenda.presentation.task.edittask

import androidx.compose.foundation.text.input.TextFieldState
import com.gkp.agenda.presentation.task.edittask.util.toUiDate
import com.gkp.agenda.presentation.task.edittask.util.toUiTime
import java.time.LocalDateTime

data class EditTaskUiState(
    val taskTitle: String = "Task",
    val taskDescription: String = "Description",
    val taskDateTime: LocalDateTime = LocalDateTime.now(),
    val taskReminderText: String = ReminderTimes.THIRTY_MINUTES_BEFORE.text,
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

enum class ReminderTimes(val text: String) {
    TEN_MINUTES_BEFORE("10 minutes before"),
    THIRTY_MINUTES_BEFORE("30 minutes before"),
    ONE_HOUR_BEFORE("1 hour before"),
    SIX_HOURS_BEFORE("2 hours before"),
    ONE_DAY_BEFORE("1 day before")
}