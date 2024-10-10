package com.gkp.agenda.presentation.edit.navigation

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.gkp.agenda.presentation.R
import com.gkp.agenda.presentation.compoments.EditAgendaFieldTitleAndDescription
import com.gkp.agenda.presentation.edit.EditAgendaItemViewModel
import com.gkp.core.designsystem.theme.TaskyTheme



@Composable
fun EditAgendaItemTitleAndDescriptionScreen(
    onClickBackButton: () -> Unit,
    viewModel: EditAgendaItemViewModel,
    textFieldName: String,
) {
    val uiState = viewModel.uiState

    EditAgendaItemTitleAndDescriptionScreen(
        onClickBackButton = onClickBackButton,
        onClickSaveButton = {
            if (textFieldName == EDIT_TEXT_FIELD_NAME_TITLE) {
                viewModel.onTitleChanged(
                    uiState.editTitleTextState.text.toString()
                )
            } else {
                viewModel.onDescriptionChanged(uiState.editDescriptionTextState.text.toString())
            }
            onClickBackButton()
        },
        textState = if (textFieldName == EDIT_TEXT_FIELD_NAME_TITLE)
            uiState.editTitleTextState
        else
            uiState.editDescriptionTextState,
        topBarTitle = if (textFieldName == EDIT_TEXT_FIELD_NAME_TITLE)
            stringResource(R.string.edit_title)
        else
            stringResource(R.string.edit_description),
        textFieldFontSize = if (textFieldName == EDIT_TEXT_FIELD_NAME_TITLE) 26.sp else 16.sp
    )

}

@Composable
private fun EditAgendaItemTitleAndDescriptionScreen(
    onClickBackButton: () -> Unit,
    onClickSaveButton: () -> Unit,
    textState: TextFieldState,
    topBarTitle: String,
    textFieldFontSize: TextUnit,
) {
    EditAgendaFieldTitleAndDescription(
        topBarTitle = topBarTitle,
        onBackClick = onClickBackButton,
        onSaveClick = onClickSaveButton,
        textState = textState,
        textFieldFontSize = textFieldFontSize
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun EditTaskTitleScreenPreview() {
    TaskyTheme {
        val textState = rememberTextFieldState()
        textState.edit {
            this.append("Tasky")
        }
        EditAgendaItemTitleAndDescriptionScreen(
            textState = textState,
            onClickBackButton = {},
            onClickSaveButton = {},
            topBarTitle = "EDIT TITLE",
            textFieldFontSize = 26.sp
        )
    }
}