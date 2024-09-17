package com.gkp.auth.presentation.register

import androidx.compose.foundation.text.input.TextFieldState
import com.gkp.auth.domain.PasswordValidationState

data class RegisterState(
    val fullNameTextState: TextFieldState = TextFieldState(),
    val emailTextState: TextFieldState = TextFieldState(),
    val passwordTextState: TextFieldState = TextFieldState(),
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val isEmailValid: Boolean = false,
    val isFullNameValid: Boolean = false,
)


