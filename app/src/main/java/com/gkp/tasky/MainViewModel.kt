package com.gkp.tasky

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gkp.auth.domain.session.SessionStorage
import kotlinx.coroutines.launch

class MainViewModel(sessionStorage: SessionStorage) : ViewModel() {
    var mainAppState by mutableStateOf(MainAppState())
        private set

    init {
        viewModelScope.launch {
            val authInfo = sessionStorage.getAuthInfo()
            mainAppState = mainAppState.copy(
                sessionStorageLoading = false,
                isLoggedIn = authInfo.accessToken.isNotEmpty()
            )
        }
    }


}