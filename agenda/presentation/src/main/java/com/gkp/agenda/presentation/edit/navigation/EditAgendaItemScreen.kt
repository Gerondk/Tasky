package com.gkp.agenda.presentation.edit.navigation

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.gkp.agenda.domain.model.AgendaItemType
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.compoments.EditAgendaItemCommonSection
import com.gkp.agenda.presentation.edit.EditAgendaItemViewModel
import com.gkp.agenda.presentation.edit.EditItemUiState
import com.gkp.core.designsystem.theme.TaskyGreen
import com.gkp.core.designsystem.theme.TaskyLightGreen
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
    val photoGalleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            viewModel.onPhotosSelected(uris)
        }
    )

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
        onAddPhotoClick = {
            photoGalleryLauncher.launch(
                PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        },
        onAddVisitorClick = viewModel::onAddVisitor,
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
    onDateSelected: (Long, Boolean) -> Unit,
    onTimeSelected: (Int, Int, Boolean) -> Unit,
    onAddPhotoClick: () -> Unit,
    onAddVisitorClick: (String) -> Unit,
    state: EditItemUiState,
    agendaItemType: AgendaItemType,
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
                AgendaItemType.EVENT -> R.string.edit_event
            }
        ),
        itemName = stringResource(
            when (agendaItemType) {
                AgendaItemType.TASK -> R.string.task
                AgendaItemType.REMINDER -> R.string.reminder
                AgendaItemType.EVENT -> R.string.event
            }
        ),
        itemLeadingBoxColor = when (agendaItemType) {
            AgendaItemType.TASK -> TaskyGreen
            AgendaItemType.REMINDER -> TaskyTextHintColor
            AgendaItemType.EVENT -> TaskyLightGreen
        },
        agendaItemType = agendaItemType,
        onAddPhotoClick = onAddPhotoClick,
        onAddVisitorClick = onAddVisitorClick,
        state = state
    )
}

