package com.gkp.auth.presentation.di

import com.gkp.auth.presentation.login.LoginViewModel
import com.gkp.auth.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}