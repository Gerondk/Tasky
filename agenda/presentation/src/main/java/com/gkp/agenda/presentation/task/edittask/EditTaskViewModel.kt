package com.gkp.agenda.presentation.task.edittask

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.gkp.agenda.presentation.task.edittask.navigation.EditTaskGraph
import com.gkp.agenda.presentation.task.edittask.util.getReminderTimeText
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class EditTaskViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val taskId: Int = savedStateHandle.toRoute<EditTaskGraph>().taskId

    var uiState by mutableStateOf(EditTaskUiState())
        private set

    fun onTaskTitleChanged(title: String) {
        uiState = uiState.copy(taskTitle = title)
    }

    fun onTaskDescriptionChanged(description: String) {
        uiState = uiState.copy(taskDescription = description)
    }

    fun onTaskReminderChanged(reminderIndex: Int) {
        uiState = uiState.copy(taskReminderTextId = getReminderTimeText(reminderIndex))
    }

    fun onTaskDateSelected(selectedDateMillis: Long) {
        val date =
            LocalDate.ofInstant(Instant.ofEpochMilli(selectedDateMillis), ZoneId.systemDefault())
        uiState = uiState.copy(taskDateTime = date.atTime(uiState.taskDateTime.toLocalTime()))

    }

    fun onTaskTimeSelected(hour: Int, minute: Int) {
        uiState = uiState.copy(
            taskDateTime = uiState.taskDateTime.withHour(hour).withMinute(minute)
        )

    }


}