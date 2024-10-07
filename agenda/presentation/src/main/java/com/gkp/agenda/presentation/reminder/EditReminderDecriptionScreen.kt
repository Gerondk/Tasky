package com.gkp.agenda.presentation.reminder

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import com.gkp.agenda.presentation.compoments.EditAgendaItemDescription
import com.gkp.agenda.presentation.EditAgendaItemViewModel

@Composable
fun EditReminderDescriptionScreen(
    onClickBackButton: () -> Unit,
    viewModel: EditAgendaItemViewModel,
) {
    val state = viewModel.uiState

    EditReminderDescriptionScreen(
        onClickBackButton = onClickBackButton,
        onClickSaveButton = {
            viewModel.onDescriptionChanged(state.editDescriptionTextState.text.toString())
            onClickBackButton()
        },
        textState = state.editDescriptionTextState
    )
}

@Composable
private fun EditReminderDescriptionScreen(
    onClickBackButton: () -> Unit,
    onClickSaveButton: () -> Unit,
    textState: TextFieldState,
) {
    EditAgendaItemDescription(
        onClickBackButton = onClickBackButton,
        onClickSaveButton = onClickSaveButton,
        textState = textState,
    )

}