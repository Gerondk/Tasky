package com.gkp.auth.data.di


import com.gkp.auth.data.RetrofitAuthRepository
import com.gkp.auth.data.session.DataStoreSessionStorage
import com.gkp.auth.domain.AuthRepository
import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.network.di.networkModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    includes(networkModule)
    singleOf(::RetrofitAuthRepository).bind<AuthRepository>()
    single<SessionStorage> { DataStoreSessionStorage(get()) }

}