package com.gkp.auth.data.di


import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.gkp.auth.data.EmailValidator
import com.gkp.auth.data.RetrofitAuthRepository
import com.gkp.auth.data.session.SharedPreferencesSessionStorage
import com.gkp.auth.domain.AuthRepository
import com.gkp.auth.domain.PatternValidator
import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.network.di.networkModule
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    includes(networkModule)
    singleOf(::RetrofitAuthRepository).bind<AuthRepository>()

    single<PatternValidator> {
        EmailValidator
    }
    single<SessionStorage> {
        SharedPreferencesSessionStorage(get(), Dispatchers.IO)
    }
    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            "auth_pref",
            MasterKey(androidApplication()),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}