package com.gkp.auth.data.di


import com.gkp.auth.data.RetrofitAuthRepository
import com.gkp.auth.domain.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    singleOf(::RetrofitAuthRepository).bind<AuthRepository>()
}