package com.gkp.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.core.designsystem.theme.TaskyGreen
import com.gkp.core.designsystem.theme.TaskyLightGray
import com.gkp.core.designsystem.theme.TaskyTextFieldColor
import com.gkp.core.designsystem.theme.TaskyTextHintColor


@Composable
fun TaskyTextField(
    modifier: Modifier = Modifier,
    hintText: String,
    textState: TextFieldState,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    inputTransformation: InputTransformation? = null,
    endIcon: ImageVector? = null,
    iconTintColor: Color = TaskyGreen,
    endIconContentDescription: String? = null,
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box {
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
                if (endIcon != null) {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = endIcon,
                            contentDescription = endIconContentDescription,
                            tint = iconTintColor
                        )
                    }
                }
            }
        },
    )
}

@Preview
@Composable
private fun TaskyTextFieldPreview() {
    val state = rememberTextFieldState()
    state.edit {
        append("test")
    }
    TaskyTextField(
        modifier = Modifier.fillMaxWidth(),
        hintText = "Email address",
        textState = state,
        endIcon = VisibilityOffIcon,
        iconTintColor = TaskyTextFieldColor
    )
}