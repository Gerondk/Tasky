package com.gkp.agenda.presentation.task.edittask

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.compoments.AgendaBackground
import com.gkp.agenda.presentation.compoments.AgendaItemDateTime
import com.gkp.agenda.presentation.compoments.AgendaItemDescription
import com.gkp.agenda.presentation.compoments.AgendaItemHeader
import com.gkp.agenda.presentation.compoments.AgendaItemReminderTime
import com.gkp.agenda.presentation.compoments.AgendaItemTitle
import com.gkp.core.designsystem.theme.TaskyTextFieldColor
import com.gkp.core.designsystem.theme.TaskyTheme
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditTaskScreen(
    onClickTaskTitle: () -> Unit,
    onClickTaskDescription: () -> Unit,
    onClickEditCloseButton: () -> Unit,
    viewModel: EditTaskViewModel,
) {
    val uiState = viewModel.uiState

    EditTaskScreen(
        onClickTaskTitle = onClickTaskTitle,
        onClickTaskDescription = onClickTaskDescription,
        onClickEditSaveButton = {},
        onClickDeleteButton = {},
        onClickEditCloseButton = onClickEditCloseButton,
        onCLickReminderMenuItem = viewModel::onTaskReminderChanged,
        onTaskDateSelected = viewModel::onTaskDateSelected,
        onTaskTimeSelected = viewModel::onTaskTimeSelected,
        state = uiState
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditTaskScreen(
    onClickTaskTitle: () -> Unit,
    onClickTaskDescription: () -> Unit,
    onClickEditCloseButton: () -> Unit,
    onClickEditSaveButton: () -> Unit,
    onClickDeleteButton: () -> Unit,
    onCLickReminderMenuItem: (Int) -> Unit,
    onTaskDateSelected: (Long) -> Unit,
    onTaskTimeSelected: (Int, Int) -> Unit,
    state: EditTaskUiState,
) {
    var showDatePicker by rememberSaveable {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()

    val timePickerState = rememberTimePickerState(
        initialHour = state.taskDateTime.hour,
        initialMinute = state.taskDateTime.minute,
        is24Hour = true
    )
    var showTimePicker by rememberSaveable {
        mutableStateOf(false)
    }

    var showReminderMenu by rememberSaveable {
        mutableStateOf(false)
    }

    AgendaBackground(
        title = stringResource(R.string.edit_task),
        navigationIcon = {
            IconButton(onClick = onClickEditCloseButton) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_button)
                )
            }
        },
        actions = {
            IconButton(onClick = { onClickEditSaveButton() }) {
                Text(
                    text = stringResource(R.string.save),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AgendaItemHeader(
                itemName = stringResource(R.string.task)
            )
            Spacer(modifier = Modifier.height(42.dp))
            AgendaItemTitle(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clickable { onClickTaskTitle() },
                itemTitle = state.taskTitle,
                editMode = true
            )
            HorizontalDivider()
            AgendaItemDescription(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clickable { onClickTaskDescription() },
                itemDescription = state.taskDescription,
                editMode = true
            )
            HorizontalDivider()
            AgendaItemDateTime(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                date = state.uiDate,
                time = state.uiTime,
                onClickDate = { showDatePicker = true },
                onClickTime = { showTimePicker = true },
                editMode = true
            )
            HorizontalDivider()
            AgendaItemReminderTime(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clickable {
                        showReminderMenu = true
                    },
                reminderTimeText = stringResource(state.taskReminderTextId),
                onCLickReminderMenuItem = onCLickReminderMenuItem,
                onDismissReminderMenu = { showReminderMenu = false },
                showReminderMenu = showReminderMenu,
                editMode = true
            )
            HorizontalDivider()
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClickDeleteButton() }
            ) {
                Text(
                    text = stringResource(R.string.delete_task),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = TaskyTextFieldColor
                    )
                )
            }
        }

    }

    TaskyDatePickerDialog(
        showDatePicker = showDatePicker,
        state = datePickerState,
        dismissDatePicker = { showDatePicker = false },
        onDateSelected = onTaskDateSelected

    )
    TaskyTimePickerWithDialog(
        state = timePickerState,
        showTimePicker = showTimePicker,
        onTimeSelected = onTaskTimeSelected,
        dismissTimePicker = { showTimePicker = false }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskyDatePickerDialog(
    showDatePicker: Boolean,
    dismissDatePicker: () -> Unit,
    onDateSelected: (Long) -> Unit,
    state: DatePickerState,
) {
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                dismissDatePicker()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        dismissDatePicker()
                        state.selectedDateMillis?.let {
                            onDateSelected(it)
                        }
                    }
                ) {
                    Text(text = stringResource(R.string.date_picker_ok))
                }
            },
        ) {
            DatePicker(
                state = state,
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskyTimePickerWithDialog(
    modifier: Modifier = Modifier,
    state: TimePickerState,
    dismissTimePicker: () -> Unit,
    onTimeSelected: (Int, Int) -> Unit,
    showTimePicker: Boolean,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showTimePicker) {
            Dialog(
                onDismissRequest = dismissTimePicker,
                properties = DialogProperties(usePlatformDefaultWidth = true)
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = MaterialTheme.shapes.extraLarge
                        ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    shape = MaterialTheme.shapes.extraLarge
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            text = stringResource(R.string.time_picker_title)
                        )
                        TimePicker(
                            state = state,
                            layoutType = TimePickerLayoutType.Vertical,
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(
                                modifier = Modifier.padding(end = 8.dp),
                                onClick = dismissTimePicker
                            ) {
                                Text(
                                    text = stringResource(R.string.time_picker_cancel)
                                )
                            }
                            Button(
                                modifier = Modifier.padding(start = 8.dp),
                                onClick = {
                                    dismissTimePicker()
                                    onTimeSelected(state.hour, state.minute)
                                }
                            ) {
                                Text(text = stringResource(R.string.time_picker_ok))
                            }
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun TaskScreenPreview() {
    TaskyTheme {
        EditTaskScreen(
            onClickEditCloseButton = {},
            onClickTaskTitle = {},
            onClickTaskDescription = {},
            onClickEditSaveButton = {},
            onClickDeleteButton = {},
            onCLickReminderMenuItem = {},
            onTaskDateSelected = {},
            onTaskTimeSelected = { i: Int, i1: Int -> },
            state = EditTaskUiState(
                taskTitle = "definitionem",
                taskDescription = "consul",
                taskDateTime = LocalDateTime.now(),
                taskReminderTextId = R.string.reminder_30_minutes_before
            )
        )
    }


}