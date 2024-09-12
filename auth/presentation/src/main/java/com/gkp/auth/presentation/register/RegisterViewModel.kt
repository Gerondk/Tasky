package com.gkp.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.auth.domain.AuthRepository
import com.gkp.core.domain.util.TaskyResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RegisterViewModel(private val authRepository: AuthRepository) : ViewModel() {
   private val _registerStateFlow = MutableStateFlow(RegisterState())
   val registerStateFlow = _registerStateFlow.asStateFlow()

   fun onGetStatedClick() {
      val fullName = registerStateFlow.value.fullNameTextState.text.toString()
      val email = registerStateFlow.value.emailTextState.text.toString()
      val password = registerStateFlow.value.passwordTextState.text.toString()
      authRepository.register(
         fullName = fullName,
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