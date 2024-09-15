package com.gkp.tasky.di

import com.gkp.auth.data.di.authDataModule
import com.gkp.auth.presentation.di.authPresentationModule
import org.koin.dsl.module

val appModule = module {
    includes(
        authPresentationModule,
        authDataModule,
    )
}