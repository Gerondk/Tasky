package com.gkp.agenda.presentation.task.edittask

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.agenda.presentation.R
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun EditDescriptionScreen(
    onBackClick: () -> Unit = {},
) {

    EditDescriptionScreen(
        onClickBackButton = onBackClick,
        onClickSaveButton = {}
    )

}

@Composable
private fun EditDescriptionScreen(
    onClickBackButton: () -> Unit = {},
    onClickSaveButton: () -> Unit = {},
    textState: TextFieldState = rememberTextFieldState(),
) {
    Spacer(modifier = Modifier.height(30.dp))
    EditAgendaField(
        title = stringResource(R.string.edit_description),
        onBackClick = onClickBackButton,
        onSaveClick = onClickSaveButton
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            state = textState,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.W400
            ),
        )
    }
}

@Preview
@Composable
private fun EditDescriptionScreenPreview() {
    val state = rememberTextFieldState()
    state.edit {
        this.append("Defdfdföllfdlöl  fkgfsöfgslöfsö")
    }
    TaskyTheme {
        EditDescriptionScreen(
            textState = state
        )
    }

}