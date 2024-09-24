package com.gkp.auth.data.session

import android.content.Context
import androidx.datastore.dataStore
import com.gkp.auth.data.toAuthInfo
import com.gkp.auth.data.toLoginResponse
import com.gkp.auth.domain.AuthInfo
import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.network.model.LoginResponse
import kotlinx.coroutines.flow.first

val defaultLoginResponse = LoginResponse(
    accessToken = "",
    refreshToken = "",
    fullName = "",
    userId = "",
    accessTokenExpirationTimestamp = 0L
)
private val Context.dataStore by dataStore(
    fileName = "auth_info.json",
    serializer = DataStoreLoginResponseSerializer(
        defaultValue = defaultLoginResponse
    )
)

/**
 * This class throws exception due the implementation of AndroidCryptoManger
 * Therefore EncryptedSharedPreferences is used
 */
class DataStoreSessionStorage(private val context: Context) : SessionStorage {
    override suspend fun saveAuthInfo(authInfo: AuthInfo) {
        context.dataStore.updateData {
            authInfo.toLoginResponse()
        }
    }

    override suspend fun getAuthInfo(): AuthInfo {
        return try {
            context.dataStore.data.first().toAuthInfo()
        } catch (e: Exception) {
            defaultLoginResponse.toAuthInfo()
        }
    }

    override suspend fun resetAuthInfo() {
        context.dataStore.updateData {
            defaultLoginResponse
        }
    }
}