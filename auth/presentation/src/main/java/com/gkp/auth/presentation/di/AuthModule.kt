package com.gkp.auth.presentation.di

import com.gkp.auth.domain.UserDataValidation
import com.gkp.auth.presentation.login.LoginViewModel
import com.gkp.auth.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authPresentationModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
    singleOf(::UserDataValidation).bind<UserDataValidation>()
}