package com.gkp.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.core.designsystem.theme.TaskyLightGray
import com.gkp.core.designsystem.theme.TaskyTextFieldColor
import com.gkp.core.designsystem.theme.TaskyTextHintColor


@Composable
fun TaskyPasswordTextField(
    modifier: Modifier = Modifier,
    hintText: String,
    textState: TextFieldState,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    inputTransformation: InputTransformation? = null,
    iconTintColor: Color = TaskyTextFieldColor,
    endIconContentDescription: String? = null,
) {
    var hasFocus by remember {
        mutableStateOf(false)
    }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    BasicSecureTextField(
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
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) VisibilityIcon else VisibilityOffIcon,
                        contentDescription = endIconContentDescription,
                        tint = iconTintColor
                    )
                }
            }
        },
        textObfuscationMode = if (isPasswordVisible)
                                TextObfuscationMode.Visible
                             else
                                TextObfuscationMode.Hidden
    )
}

@Preview
@Composable
private fun TaskyPasswordTextFieldPreview() {
    val state = rememberTextFieldState()
    state.edit {
        append("test")
    }
    TaskyPasswordTextField(
        modifier = Modifier.fillMaxWidth(),
        hintText = "Email address",
        textState = state,
        iconTintColor = TaskyTextFieldColor
    )
}