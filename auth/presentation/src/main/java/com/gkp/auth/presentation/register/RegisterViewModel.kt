package com.gkp.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.auth.domain.AuthRepository
import com.gkp.auth.domain.UserDataValidation
import com.gkp.auth.presentation.util.textAsFlow
import com.gkp.core.domain.util.TaskyResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel(
    private val authRepository: AuthRepository,
    private val userDataValidation: UserDataValidation,
) : ViewModel() {
    private val eventsChannel = Channel<RegisterEvents>()
    val eventsFlow = eventsChannel.receiveAsFlow()

    private val _registerStateFlow = MutableStateFlow(RegisterState())
    val registerStateFlow = _registerStateFlow.asStateFlow()

    init {
        registerStateFlow.value.fullNameTextState.textAsFlow()
            .onEach { fullName ->
                _registerStateFlow.update { state ->
                    state.copy(
                        isFullNameValid = userDataValidation.isFullNameValid(fullName)
                    )
                }
            }
            .launchIn(viewModelScope)

        registerStateFlow.value.emailTextState.textAsFlow()
            .onEach { email ->
                _registerStateFlow.update { state ->
                    state.copy(
                        isEmailValid = userDataValidation.isEmailValid(email)
                    )
                }
            }
            .launchIn(viewModelScope)

        registerStateFlow.value.passwordTextState.textAsFlow()
            .onEach { password ->
                _registerStateFlow.update { state ->
                    state.copy(
                        passwordValidationState = userDataValidation.validatePassword(password)
                    )
                }
            }
            .launchIn(viewModelScope)
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
                    // TODO: Proper error handling
                    eventsChannel.send(
                        RegisterEvents.RegisterError("")
                    )
                }

                TaskyResult.Loading -> {
                }

                is TaskyResult.Success -> {
                    eventsChannel.send(RegisterEvents.RegisterSuccess)
                }
            }
        }.launchIn(viewModelScope)
    }

}