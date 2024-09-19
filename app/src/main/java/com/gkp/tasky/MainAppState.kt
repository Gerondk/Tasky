package com.gkp.tasky

data class MainAppState(
    val sessionStorageLoading: Boolean = true,
    val isUserLoggedIn: Boolean = false,
)
