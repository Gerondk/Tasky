package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.gkp.agenda.presentation.R

@Composable
fun EditAgendaItemTitle(
    onClickBackButton: () -> Unit,
    onClickSaveButton: () -> Unit,
    textState: TextFieldState,
) {
    EditAgendaField(
        title = stringResource(R.string.edit_title),
        onBackClick = onClickBackButton,
        onSaveClick = onClickSaveButton,
        textState = textState,
        textFieldFontSize = 26.sp
    )
}