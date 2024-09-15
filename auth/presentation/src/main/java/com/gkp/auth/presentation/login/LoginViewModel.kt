package com.gkp.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.auth.domain.AuthRepository
import com.gkp.core.domain.util.TaskyResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginViewModel(private  val authRepository: AuthRepository) : ViewModel() {
    private val _loginStateFlow = MutableStateFlow(LoginState())
    val loginState = _loginStateFlow.asStateFlow()

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