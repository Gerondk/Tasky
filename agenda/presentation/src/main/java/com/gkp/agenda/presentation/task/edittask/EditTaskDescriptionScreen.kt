package com.gkp.agenda.presentation.task.edittask

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gkp.agenda.presentation.EditAgendaItemViewModel
import com.gkp.agenda.presentation.compoments.EditAgendaItemDescription
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun EditTaskDescriptionScreen(
    onBackClick: () -> Unit = {},
    viewModel: EditAgendaItemViewModel,
) {
    val uiState = viewModel.uiState

    EditTaskDescriptionScreen(
        onClickBackButton = onBackClick,
        onClickSaveButton = {
            viewModel.onDescriptionChanged(
                uiState.editDescriptionTextState.text.toString()
            )
            onBackClick()
        },
        textState = uiState.editDescriptionTextState
    )
}

@Composable
private fun EditTaskDescriptionScreen(
    onClickBackButton: () -> Unit,
    onClickSaveButton: () -> Unit,
    textState: TextFieldState,
) {
   EditAgendaItemDescription(
       onClickBackButton = onClickBackButton,
       onClickSaveButton = onClickSaveButton,
       textState = textState
   )
}

@Preview
@Composable
private fun EditTaskDescriptionScreenPreview() {
    val state = rememberTextFieldState()
    state.edit {
        this.append("Defdfdföllfdlöl  fkgfsöfgslöfsö")
    }
    TaskyTheme {
        EditTaskDescriptionScreen(
            textState = state,
            onClickBackButton = {},
            onClickSaveButton = {}
        )
    }

}