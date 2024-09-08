package com.gkp.tasky.di

import com.gkp.auth.presentation.di.authModule
import org.koin.dsl.module

val appModule = module {
    includes(
        authModule
    )
}