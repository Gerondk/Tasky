package com.gkp.agenda.presentation.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.gkp.agenda.domain.AgendaRepository
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.presentation.task.edittask.navigation.EditTaskGraph
import com.gkp.agenda.presentation.task.edittask.util.getReminderInDateLong
import com.gkp.agenda.presentation.task.edittask.util.getReminderTimeText
import com.gkp.agenda.presentation.task.edittask.util.toMillis
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.UUID

class EditAgendaItemViewModel(
    savedStateHandle: SavedStateHandle,
    private val agendaRepository: AgendaRepository,
) : ViewModel() {
    private val taskId: Int = savedStateHandle.toRoute<EditTaskGraph>().taskId

    var uiState by mutableStateOf(EditItemUiState())
        private set

    fun onTitleChanged(title: String) {
        uiState = uiState.copy(title = title)
    }

    fun onDescriptionChanged(description: String) {
        uiState = uiState.copy(description = description)
    }

    fun onReminderTimeChanged(reminderIndex: Int) {
        uiState = uiState.copy(reminderTextId = getReminderTimeText(reminderIndex))
    }

    fun onDateSelected(selectedDateMillis: Long) {
        val date =
            LocalDate.ofInstant(Instant.ofEpochMilli(selectedDateMillis), ZoneId.systemDefault())
        uiState = uiState.copy(dateTime = date.atTime(uiState.dateTime.toLocalTime()))

    }

    fun onTimeSelected(hour: Int, minute: Int) {
        uiState = uiState.copy(
            dateTime = uiState.dateTime.withHour(hour).withMinute(minute)
        )
    }

    fun onSave(agendaItem: AgendaItem) {
        val savedAgendaItem = when (agendaItem) {
            is AgendaItem.Reminder -> {
                AgendaItem.Reminder(
                    id = UUID.randomUUID().toString(),
                    title = uiState.title,
                    description = uiState.description,
                    time = uiState.dateTime.toMillis(),
                    remindAt = getReminderInDateLong(
                        selectedDate = uiState.dateTime,
                        reminderTimeMenuTextId = uiState.reminderTextId
                    ),
                )
            }

            is AgendaItem.Task -> {
                AgendaItem.Task(
                    id = UUID.randomUUID().toString(),
                    title = uiState.title,
                    description = uiState.description,
                    time = uiState.dateTime.toMillis(),
                    remindAt = getReminderInDateLong(
                        selectedDate = uiState.dateTime,
                        reminderTimeMenuTextId = uiState.reminderTextId
                    ),
                    isDone = false
                )
            }
        }
        agendaRepository.addAgendaItem(savedAgendaItem)
    }


}