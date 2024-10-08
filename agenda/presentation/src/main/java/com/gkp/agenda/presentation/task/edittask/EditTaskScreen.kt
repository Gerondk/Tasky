package com.gkp.agenda.presentation.task.edittask

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.presentation.edit.EditItemUiState
import com.gkp.agenda.presentation.edit.EditAgendaItemViewModel
import com.gkp.agenda.presentation.compoments.EditAgendaItemCommonSection

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditTaskScreen(
    onClickTaskTitle: () -> Unit,
    onClickTaskDescription: () -> Unit,
    onClickEditCloseButton: () -> Unit,
    viewModel: EditAgendaItemViewModel,
) {
    val uiState = viewModel.uiState

    EditTaskScreen(
        onClickTitle = onClickTaskTitle,
        onClickDescription = onClickTaskDescription,
        onClickEditSaveButton = {
            viewModel.onSave(AgendaItem.Task())
            onClickEditCloseButton()
        },
        onClickDeleteButton = {},
        onClickEditCloseButton = onClickEditCloseButton,
        onCLickReminderMenuItem = viewModel::onReminderTimeChanged,
        onDateSelected = viewModel::onDateSelected,
        onTimeSelected = viewModel::onTimeSelected,
        state = uiState
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun EditTaskScreen(
    onClickTitle: () -> Unit,
    onClickDescription: () -> Unit,
    onClickEditCloseButton: () -> Unit,
    onClickEditSaveButton: () -> Unit,
    onClickDeleteButton: () -> Unit,
    onCLickReminderMenuItem: (Int) -> Unit,
    onDateSelected: (Long) -> Unit,
    onTimeSelected: (Int, Int) -> Unit,
    state: EditItemUiState
) {
    EditAgendaItemCommonSection(
        onClickTitle = onClickTitle,
        onClickDescription = onClickDescription,
        onClickEditCloseButton = onClickEditCloseButton,
        onClickEditSaveButton = onClickEditSaveButton,
        onClickDeleteButton = onClickDeleteButton,
        onCLickReminderMenuItem = onCLickReminderMenuItem,
        onDateSelected = onDateSelected,
        onTimeSelected = onTimeSelected,
        state = state
    )
}

