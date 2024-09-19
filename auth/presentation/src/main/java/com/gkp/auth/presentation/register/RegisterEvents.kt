package com.gkp.auth.presentation.register

sealed interface RegisterEvents {
    data class RegisterError(val error: String) : RegisterEvents
    data object RegisterSuccess : RegisterEvents
    data object RegisterLoading : RegisterEvents
}