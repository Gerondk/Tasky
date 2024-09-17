package com.gkp.auth.presentation.login

import androidx.compose.foundation.text.input.TextFieldState

data class LoginState(
    val emailTextFieldState: TextFieldState = TextFieldState(),
    val passwordTextFieldState: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
)



