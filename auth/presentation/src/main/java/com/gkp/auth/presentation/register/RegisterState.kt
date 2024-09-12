package com.gkp.auth.presentation.register

import androidx.compose.foundation.text.input.TextFieldState

data class RegisterState(
    val fullNameTextState: TextFieldState = TextFieldState(),
    val emailTextState: TextFieldState = TextFieldState(),
    val passwordTextState: TextFieldState = TextFieldState()
)
