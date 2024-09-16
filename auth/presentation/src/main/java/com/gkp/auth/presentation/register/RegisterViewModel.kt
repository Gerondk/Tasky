package com.gkp.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.auth.domain.AuthRepository
import com.gkp.auth.domain.UserDataValidation
import com.gkp.auth.presentation.util.textAsFlow
import com.gkp.core.domain.util.TaskyResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class RegisterViewModel(
    private val authRepository: AuthRepository,
    private val userDataValidation: UserDataValidation,
) : ViewModel() {
    private val _registerStateFlow = MutableStateFlow(RegisterState())
    val registerStateFlow = _registerStateFlow.asStateFlow()

    init {
        combine(
            registerStateFlow.value.fullNameTextState.textAsFlow(),
            registerStateFlow.value.emailTextState.textAsFlow(),
            registerStateFlow.value.passwordTextState.textAsFlow()
        ) { fullName, email, password ->
            _registerStateFlow.update { state ->
                state.copy(
                    isFullNameValid = userDataValidation.isFullNameValid(fullName),
                    isEmailValid = userDataValidation.isEmailValid(email),
                    passwordValidationState = userDataValidation.validatePassword(password)
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onGetStatedClick() {
        val fullName = registerStateFlow.value.fullNameTextState.text.toString()
        val email = registerStateFlow.value.emailTextState.text.toString()
        val password = registerStateFlow.value.passwordTextState.text.toString()
        authRepository.register(
            fullName = fullName,
            email = email,
            password = password
        ).onEach {
            when (it) {
                is TaskyResult.Error -> {
                }

                TaskyResult.Loading -> {
                }

                is TaskyResult.Success -> {
                }
            }
        }.launchIn(viewModelScope)
    }

}