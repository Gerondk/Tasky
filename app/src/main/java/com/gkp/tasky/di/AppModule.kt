package com.gkp.tasky.di

import com.gkp.auth.data.di.authDataModule
import com.gkp.auth.presentation.di.authModule
import com.gkp.core.network.di.networkModule
import org.koin.dsl.module

val appModule = module {
    includes(
        authModule,
        authDataModule,
        networkModule
    )
}