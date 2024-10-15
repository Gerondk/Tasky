package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.gkp.agenda.presentation.R
import com.gkp.core.designsystem.theme.TaskyGray
import com.gkp.core.designsystem.theme.TaskyLightGray
import com.gkp.core.designsystem.theme.TaskyTextFieldColor
import com.gkp.core.designsystem.theme.TaskyTextHintColor

@Composable
fun EventVisitorsSection(
    modifier: Modifier = Modifier,
    onAddVisitorClick: (String) -> Unit,
    visitors: List<String>,
) {
    var showDialog by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.event_visitors),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.W600,
                    fontSize = 20.sp
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = modifier
                    .size(35.dp)
                    .background(
                        color = TaskyLightGray,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clickable {
                        showDialog = true
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = TaskyGray
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        visitors.forEach { visitor ->
            VisitorItem(visitor = visitor)
        }
    }
    AddVisitorDialog(
        onDismiss = { showDialog = false },
        showDialog = showDialog,
        onAddVisitorClick = onAddVisitorClick,
    )
}

@Composable
fun VisitorItem(visitor: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = TaskyLightGray,
                shape = RoundedCornerShape(5.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = visitor,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 16.sp
            )
        )
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = null,
                tint = TaskyGray
            )
        }
    }
}

@Composable
fun AddVisitorDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    showDialog: Boolean,
    onAddVisitorClick: (String) -> Unit,
) {
    val visitorNameTextState = rememberTextFieldState()

    if (showDialog) {
        Dialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.extraLarge
                    )
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = stringResource(R.string.add_visitor),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.W600,
                        fontSize = 20.sp
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(16.dp))
                TaskyTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    hintText = stringResource(R.string.visitor_name),
                    textState = visitorNameTextState
                )
                Spacer(modifier = Modifier.size(16.dp))
                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    buttonLabel = stringResource(R.string.add_visitor_button_label),
                    onClick = {
                        onAddVisitorClick(visitorNameTextState.text.toString())
                        onDismiss()
                    }
                )
            }
        }
    }

}

@Composable
fun TaskyTextField(
    modifier: Modifier = Modifier,
    hintText: String,
    textState: TextFieldState,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    inputTransformation: InputTransformation? = null,
) {
    var hasFocus by remember {
        mutableStateOf(false)
    }

    BasicTextField(
        modifier = modifier
            .defaultMinSize(minHeight = 63.dp)
            .onFocusChanged {
                hasFocus = it.hasFocus
            }
            .background(
                color = TaskyLightGray,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(8.dp),
        state = textState,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = TaskyTextFieldColor
        ),
        keyboardOptions = keyboardOptions,
        inputTransformation = inputTransformation,
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = { innerBox ->
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = modifier.weight(1f)) {
                    if (textState.text.isEmpty() && !hasFocus) {
                        Text(
                            text = hintText,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = TaskyTextHintColor
                            )
                        )
                    }
                    innerBox()
                }

            }
        },
    )
}

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    buttonLabel: String,
    onClick: () -> Unit,
    textStyle: TextStyle = TextStyle.Default,
) {
    Button(
        modifier = modifier.defaultMinSize(minHeight = 55.dp),
        onClick = onClick,
        shape = RoundedCornerShape(38.dp)
    ) {
        Text(
            text = buttonLabel,
            style = textStyle
        )
    }
}

