package com.gkp.agenda.presentation.edit

import android.net.Uri
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.gkp.agenda.domain.AgendaRepository
import com.gkp.agenda.domain.alarm.AlarmScheduler
import com.gkp.agenda.domain.image.ImageReader
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.domain.model.AgendaItemType
import com.gkp.agenda.domain.util.toLocalDateTime
import com.gkp.agenda.presentation.edit.navigation.EditAgendaItemGraph
import com.gkp.agenda.presentation.edit.util.getReminderInDateLong
import com.gkp.agenda.presentation.edit.util.getReminderTimeText
import com.gkp.agenda.presentation.edit.util.toMillis
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.UUID

@Suppress("OPT_IN_USAGE")
class EditAgendaItemViewModel(
    savedStateHandle: SavedStateHandle,
    private val agendaRepository: AgendaRepository,
    private val imageReader: ImageReader,
    private val alarmScheduler: AlarmScheduler,
) : ViewModel() {
    private val agendaItemId = savedStateHandle.toRoute<EditAgendaItemGraph>().agendaItemId
    private val agendaItemType = savedStateHandle.toRoute<EditAgendaItemGraph>().agendaItemType

    var uiState by mutableStateOf(EditItemUiState())
        private set

    init {
        uiState = uiState.copy(agendaItemType = agendaItemType)
        snapshotFlow {
            agendaItemId
        }
            .filterNotNull()
            .flatMapLatest {
                when (agendaItemType) {
                    AgendaItemType.REMINDER -> agendaRepository.fetchAgendaItemReminder(it)
                    AgendaItemType.TASK -> agendaRepository.fetchAgendaItemTask(it)
                    AgendaItemType.EVENT -> TODO("Event edit not implemented yet")
                }
            }
            .onEach { result ->
                val agendaItemTask = result.getDataOrNull()
                agendaItemTask?.let {
                    uiState = uiState.copy(
                        id = it.id,
                        title = it.title,
                        description = it.description ?: "",
                        dateTime = it.time.toLocalDateTime(),
                        editTitleTextState = TextFieldState(
                            it.title
                        ),
                        editDescriptionTextState = TextFieldState(
                            it.description ?: ""
                        )
                    )
                }
            }.launchIn(viewModelScope)
    }


    fun onTitleChanged(title: String) {
        uiState = uiState.copy(title = title)
    }

    fun onDescriptionChanged(description: String) {
        uiState = uiState.copy(description = description)
    }

    fun onReminderTimeChanged(reminderIndex: Int) {
        uiState = uiState.copy(reminderTextId = getReminderTimeText(reminderIndex))
    }

    fun onDateSelected(selectedDateMillis: Long, toDateTimeSelected: Boolean) {
        val date =
            LocalDate.ofInstant(Instant.ofEpochMilli(selectedDateMillis), ZoneId.systemDefault())
        uiState = if (toDateTimeSelected)
            uiState.copy(toDateTime = date.atTime(uiState.toDateTime.toLocalTime()))
        else
            uiState.copy(dateTime = date.atTime(uiState.dateTime.toLocalTime()))

    }

    fun onTimeSelected(hour: Int, minute: Int, toDateTimeSelected: Boolean) {
        uiState = if (toDateTimeSelected)
            uiState.copy(
                toDateTime = uiState.toDateTime.withHour(hour).withMinute(minute)
            )
        else
            uiState.copy(
                dateTime = uiState.dateTime.withHour(hour).withMinute(minute)
            )
    }

    fun onSave() {
        val savedAgendaItem = when (agendaItemType) {
            AgendaItemType.REMINDER -> {
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

            AgendaItemType.TASK -> {
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

            AgendaItemType.EVENT -> {
                AgendaItem.Event(
                    id = UUID.randomUUID().toString(),
                    title = uiState.title,
                    description = uiState.description,
                    time = uiState.dateTime.toMillis(),
                    to = uiState.toDateTime.toMillis(),
                    remindAt = getReminderInDateLong(
                        selectedDate = uiState.dateTime,
                        reminderTimeMenuTextId = uiState.reminderTextId
                    ),
                    attendeesIds = uiState.eventVisitors,
                    photos = uiState.eventPhotoInfoList.map { it.byteArray }
                )
            }
        }
        agendaRepository.addAgendaItem(savedAgendaItem)
        alarmScheduler.schedule(savedAgendaItem)
    }

    fun onPhotosSelected(uris: List<Uri>) {
        viewModelScope.launch {
            val imageInfoList = imageReader.readImagesFromStringUri(uris.map { it.toString() })
            val imageInfoCurrentList = uiState.eventPhotoInfoList
            uiState = uiState.copy(
                eventPhotoInfoList = imageInfoCurrentList + imageInfoList
            )
        }
    }

    fun onAddVisitor(visitor: String) {
        val currentVisitors = uiState.eventVisitors
        uiState = uiState.copy(
            eventVisitors = currentVisitors + visitor
        )

    }

}