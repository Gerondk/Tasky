package com.gkp.agenda.presentation.task.edittask

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gkp.agenda.presentation.EditAgendaItemViewModel
import com.gkp.agenda.presentation.compoments.EditAgendaItemTitle
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun EditTitleScreen(
    onClickBackButton: () -> Unit,
    viewModel: EditAgendaItemViewModel
) {
    val uiState = viewModel.uiState

    EditTitleScreen(
        onClickBackButton = onClickBackButton,
        onClickSaveButton = {
            viewModel.onTitleChanged(
                uiState.editTitleTextState.text.toString()
            )
            onClickBackButton()
        },
        textState = uiState.editTitleTextState
    )

}

@Composable
private fun EditTitleScreen(
    onClickBackButton: () -> Unit,
    onClickSaveButton: () -> Unit,
    textState: TextFieldState,
) {
    EditAgendaItemTitle(
        onClickBackButton = onClickBackButton,
        onClickSaveButton = onClickSaveButton,
        textState = textState
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
        EditTitleScreen(
            textState = textState,
            onClickBackButton = {},
            onClickSaveButton = {}
        )
    }
}