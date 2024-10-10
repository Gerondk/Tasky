package com.gkp.agenda.presentation.edit.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.edit.EditItemUiState
import com.gkp.agenda.presentation.edit.EditAgendaItemViewModel
import com.gkp.agenda.presentation.compoments.EditAgendaItemCommonSection
import com.gkp.agenda.presentation.detail.navigation.AgendaItemType
import com.gkp.core.designsystem.theme.TaskyGreen
import com.gkp.core.designsystem.theme.TaskyTextHintColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditAgendaItemScreen(
    onClickAgendaItemTitle: () -> Unit,
    onClickAgendaItemDescription: () -> Unit,
    onClickEditCloseButton: () -> Unit,
    viewModel: EditAgendaItemViewModel,
) {
    val uiState = viewModel.uiState

    EditAgendaItemScreen(
        onClickTitle = onClickAgendaItemTitle,
        onClickDescription = onClickAgendaItemDescription,
        onClickEditSaveButton = {
            viewModel.onSave()
            onClickEditCloseButton()
        },
        onClickDeleteButton = {},
        onClickEditCloseButton = onClickEditCloseButton,
        onCLickReminderMenuItem = viewModel::onReminderTimeChanged,
        onDateSelected = viewModel::onDateSelected,
        onTimeSelected = viewModel::onTimeSelected,
        agendaItemType = uiState.agendaItemType,
        state = uiState
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun EditAgendaItemScreen(
    onClickTitle: () -> Unit,
    onClickDescription: () -> Unit,
    onClickEditCloseButton: () -> Unit,
    onClickEditSaveButton: () -> Unit,
    onClickDeleteButton: () -> Unit,
    onCLickReminderMenuItem: (Int) -> Unit,
    onDateSelected: (Long) -> Unit,
    onTimeSelected: (Int, Int) -> Unit,
    state: EditItemUiState,
    agendaItemType: AgendaItemType
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
        appBarTitle = stringResource(
            when (agendaItemType) {
                AgendaItemType.TASK -> R.string.edit_task
                AgendaItemType.REMINDER -> R.string.edit_reminder
            }
        ),
        itemName = stringResource(
            when (agendaItemType) {
                AgendaItemType.TASK -> R.string.task
                AgendaItemType.REMINDER -> R.string.reminder
            }
        ),
        itemLeadingBoxColor = when (agendaItemType) {
            AgendaItemType.TASK -> TaskyGreen
            AgendaItemType.REMINDER -> TaskyTextHintColor
        },
        state = state
    )
}

