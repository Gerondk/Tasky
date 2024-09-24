package com.gkp.auth.data.session

import android.content.SharedPreferences
import com.gkp.auth.data.toAuthInfo
import com.gkp.auth.data.toLoginResponse
import com.gkp.auth.domain.AuthInfo
import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.network.model.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val AUTH_INFO_KEY = "auth_info"

class SharedPreferencesSessionStorage(
    private val sharedPreferences: SharedPreferences,
    private val dispatcher: CoroutineDispatcher,
) : SessionStorage {
    override suspend fun saveAuthInfo(authInfo: AuthInfo) {
        withContext(dispatcher) {
            val authInfoJson = Json.encodeToString(authInfo.toLoginResponse())
            sharedPreferences.edit().putString(AUTH_INFO_KEY, authInfoJson).commit()
        }
    }

    override suspend fun getAuthInfo(): AuthInfo {
        return withContext(dispatcher) {
            val authInfoDefault = Json.encodeToString(defaultLoginResponse)
            val authInfoJson = sharedPreferences.getString(AUTH_INFO_KEY, authInfoDefault)
            authInfoJson?.let {
                Json.decodeFromString<LoginResponse>(authInfoJson).toAuthInfo()
            } ?: defaultLoginResponse.toAuthInfo()
        }

    }
    override suspend fun resetAuthInfo() {
        withContext(dispatcher) {
            sharedPreferences.edit().remove(AUTH_INFO_KEY).commit()
        }
    }
}


