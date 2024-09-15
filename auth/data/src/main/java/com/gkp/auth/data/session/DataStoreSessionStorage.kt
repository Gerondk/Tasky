package com.gkp.auth.data.session

import android.content.Context
import androidx.datastore.dataStore
import com.gkp.auth.domain.AuthInfo
import com.gkp.auth.domain.resetAuthInfo
import com.gkp.auth.domain.session.SessionStorage
import kotlinx.coroutines.flow.first

private val Context.dataStore by dataStore(
    fileName = "auth_info.json",
    serializer = DataStoreAuthInfoSerializer(
        defaultValue = resetAuthInfo()
    )
)

class DataStoreSessionStorage(private val context: Context) : SessionStorage {
    override suspend fun saveAuthInfo(authInfo: AuthInfo) {
        context.dataStore.updateData {
            authInfo
        }
    }

    override suspend fun getAuthInfo(): AuthInfo {
        return context.dataStore.data.first()
    }
}