package com.gkp.agenda.presentation.reminder

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.compoments.EditAgendaItemCommonSection
import com.gkp.agenda.presentation.edit.EditItemUiState
import com.gkp.agenda.presentation.edit.EditAgendaItemViewModel
import com.gkp.core.designsystem.theme.TaskyTextHintColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditReminderScreen(
    onClickEditCloseButton: () -> Unit,
    onClickTitle: () -> Unit,
    onClickDescription: () -> Unit,
    viewModel: EditAgendaItemViewModel,
) {
    val state = viewModel.uiState

    EditReminderScreen(
        onClickEditCloseButton = onClickEditCloseButton,
        onClickTitle = onClickTitle,
        onClickDescription = onClickDescription,
        onClickEditSaveButton = {
            viewModel.onSave(AgendaItem.Reminder())
            onClickEditCloseButton()
        },
        onClickDeleteButton = {},
        onCLickReminderMenuItem = viewModel::onReminderTimeChanged,
        onDateSelected = viewModel::onDateSelected,
        onTimeSelected = viewModel::onTimeSelected,
        state = state,
    )

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun EditReminderScreen(
    onClickTitle: () -> Unit,
    onClickDescription: () -> Unit,
    onClickEditCloseButton: () -> Unit,
    onClickEditSaveButton: () -> Unit,
    onClickDeleteButton: () -> Unit,
    onCLickReminderMenuItem: (Int) -> Unit,
    onDateSelected: (Long) -> Unit,
    onTimeSelected: (Int, Int) -> Unit,
    state: EditItemUiState,
    appBarTitle: String = stringResource(R.string.edit_reminder),
    itemName: String = stringResource(R.string.reminder),
    itemLeadingBoxColor: Color = TaskyTextHintColor,

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
        state = state,
        appBarTitle = appBarTitle,
        itemName = itemName,
        itemLeadingBoxColor = itemLeadingBoxColor
    )
}