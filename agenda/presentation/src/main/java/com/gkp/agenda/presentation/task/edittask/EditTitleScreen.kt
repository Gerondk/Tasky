package com.gkp.agenda.presentation.task.edittask

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gkp.agenda.presentation.R
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun EditTitleScreen(
    onClickBackButton: () -> Unit = {},
) {
    EditTitleScreen(
        onClickBackButton = onClickBackButton,
        onClickSaveButton = {}
    )

}

@Composable
private fun EditTitleScreen(
    onClickBackButton: () -> Unit = {},
    onClickSaveButton: () -> Unit = {},
    textState: TextFieldState = rememberTextFieldState(),
){
    EditAgendaField(
        title = stringResource(R.string.edit_title),
        onBackClick = onClickBackButton,
        onSaveClick = onClickSaveButton
    ){
        Spacer(modifier = Modifier.height(20.dp))
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            state = textState,
            lineLimits = TextFieldLineLimits.SingleLine,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 26.sp,
                fontWeight = FontWeight.W400
            )
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun EditTitleScreenPreview() {
    TaskyTheme {
        val textState = rememberTextFieldState()
        textState.edit {
            this.append("Tasky")
        }
        EditTitleScreen(
            textState = textState
        )
    }
}