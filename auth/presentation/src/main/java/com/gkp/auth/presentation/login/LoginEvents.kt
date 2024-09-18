package com.gkp.auth.presentation.login

interface LoginEvents {
    data object LoginEventsSuccess : LoginEvents
    data object LoginEventsLoading : LoginEvents
    data class LoginEventsError(val error: String) : LoginEvents
}