package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gkp.agenda.presentation.R
import com.gkp.core.designsystem.theme.TaskyBlack
import com.gkp.core.designsystem.theme.TaskyGreen
import com.gkp.core.designsystem.theme.TaskyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAgendaFieldTitleAndDescription(
    modifier: Modifier = Modifier,
    topBarTitle: String,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    textState: TextFieldState,
    textFieldFontSize: TextUnit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                            contentDescription = stringResource(R.string.arrow_back)
                        )
                    }
                },
                title = {
                    Text(
                        text = topBarTitle,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = TaskyBlack,
                            fontWeight = FontWeight.W600
                        )
                    )
                },
                actions = {
                    IconButton(onClick = onSaveClick) {
                        Text(
                            text = stringResource(R.string.save),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = TaskyGreen,
                                fontWeight = FontWeight.W600
                            )
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            HorizontalDivider()
            val focusRequester = remember { FocusRequester() }
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
            Spacer(modifier = Modifier.height(20.dp))
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                state = textState,
                lineLimits = TextFieldLineLimits.SingleLine,
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = textFieldFontSize,
                    fontWeight = FontWeight.W400
                )
            )
        }
    }
}

@Preview
@Composable
private fun EditAgendaFieldPreview() {
    TaskyTheme {
        EditAgendaFieldTitleAndDescription(
            topBarTitle = "EDIT TITLE",
            onBackClick = {},
            onSaveClick = {},
            textState = remember { TextFieldState() },
            textFieldFontSize = 26.sp
        )
    }
}

