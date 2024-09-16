package com.gkp.auth.domain

data class PasswordValidationState(
    val hasMinimumLength : Boolean = false,
    val hasUppercase : Boolean = false,
    val hasLowercase : Boolean = false,
    val hasDigit : Boolean = false,
) {
    val isValid : Boolean
        get() = hasMinimumLength && hasUppercase && hasLowercase && hasDigit

}