package com.gkp.agenda.presentation.reminder

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.gkp.agenda.presentation.edit.EditAgendaItemViewModel
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.compoments.EditAgendaFieldTitleAndDescription
import com.gkp.agenda.presentation.reminder.navigation.EDIT_TEXT_FIELD_NAME_TITLE

@Composable
fun EditReminderTitleAndDescriptionScreen(
    onClickBackButton: () -> Unit,
    viewModel: EditAgendaItemViewModel,
    textFieldName: String,
) {
    val state = viewModel.uiState


    EditReminderTitleAndDescriptionScreen(
        onClickBackButton = onClickBackButton,
        onClickSaveButton = {
            if (textFieldName == EDIT_TEXT_FIELD_NAME_TITLE) {
                viewModel.onTitleChanged(state.editTitleTextState.text.toString())
            } else {
                viewModel.onDescriptionChanged(state.editDescriptionTextState.text.toString())
            }
            onClickBackButton()
        },
        textState = if (textFieldName == EDIT_TEXT_FIELD_NAME_TITLE)
            state.editTitleTextState
        else
            state.editDescriptionTextState,
        topBarTitle = if (textFieldName == EDIT_TEXT_FIELD_NAME_TITLE)
            stringResource(R.string.edit_title)
        else
            stringResource(R.string.edit_description),
        textFieldFontSize = if (textFieldName == EDIT_TEXT_FIELD_NAME_TITLE) 26.sp else 16.sp
    )
}

@Composable
private fun EditReminderTitleAndDescriptionScreen(
    onClickBackButton: () -> Unit,
    onClickSaveButton: () -> Unit,
    textState: TextFieldState,
    topBarTitle: String,
    textFieldFontSize: TextUnit,
) {
    EditAgendaFieldTitleAndDescription(
        onBackClick = onClickBackButton,
        onSaveClick = onClickSaveButton,
        textState = textState,
        topBarTitle = topBarTitle,
        textFieldFontSize = textFieldFontSize
    )
}