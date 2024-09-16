package com.gkp.auth.presentation.login

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

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userDataValidation: UserDataValidation
) : ViewModel() {
    private val _loginStateFlow = MutableStateFlow(LoginState())
    val loginState = _loginStateFlow.asStateFlow()

    init {
        combine(
            loginState.value.emailTextFieldState.textAsFlow(),
            loginState.value.passwordTextFieldState.textAsFlow()
        ){ email, password ->
            _loginStateFlow.update { state ->
                state.copy(
                    isEmailValid = userDataValidation.isEmailValid(email),
                    isPasswordValid = password.isNotEmpty()
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onLoginButtonClick() {
        val email = loginState.value.emailTextFieldState.text.toString()
        val password = loginState.value.passwordTextFieldState.text.toString()
        authRepository.login(
            email = email,
            password = password
        ).onEach {
            when(it) {
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