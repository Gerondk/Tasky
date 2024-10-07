package com.gkp.agenda.presentation.reminder

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import com.gkp.agenda.presentation.compoments.EditAgendaItemTitle
import com.gkp.agenda.presentation.EditAgendaItemViewModel

@Composable
fun EditReminderTitleScreen(
    onClickBackButton: () -> Unit,
    viewModel: EditAgendaItemViewModel
) {
    val state = viewModel.uiState

     EditReminderTitleScreen(
         onClickBackButton = onClickBackButton,
         onClickSaveButton = {
             viewModel.onTitleChanged(state.editTitleTextState.text.toString())
             onClickBackButton()
         },
         textState = state.editTitleTextState,
     )
}

@Composable
private fun EditReminderTitleScreen(
    onClickBackButton: () -> Unit,
    onClickSaveButton: () -> Unit,
    textState: TextFieldState,
) {
    EditAgendaItemTitle(
        onClickBackButton = onClickBackButton,
        onClickSaveButton = onClickSaveButton,
        textState = textState,
    )

}