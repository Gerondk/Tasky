package com.gkp.auth.domain

class UserDataValidation (
    private val validator: PatternValidator
) {
    fun isFullNameValid(fullName: String): Boolean {
        return fullName.length in MIN_FULL_NAME_LENGTH..MAX_FULL_NAME_LENGTH
    }

    fun isEmailValid(email: String): Boolean {
        return  validator.validate(email)
    }

    fun validatePassword(password: String): PasswordValidationState {
        val hasMinLength = password.length >= MIN_PASSWORD_LENGTH
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }

        return PasswordValidationState(
            hasMinimumLength = hasMinLength,
            hasUppercase = hasUpperCase,
            hasLowercase = hasLowerCase,
            hasDigit = hasDigit
        )
    }

    companion object{
        const val MIN_FULL_NAME_LENGTH = 4
        const val MAX_FULL_NAME_LENGTH = 50
        const val MIN_PASSWORD_LENGTH = 9
    }
}