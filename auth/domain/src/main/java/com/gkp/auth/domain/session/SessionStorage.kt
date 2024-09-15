package com.gkp.auth.domain.session

import com.gkp.auth.domain.AuthInfo

interface SessionStorage {
    suspend fun saveAuthInfo(authInfo: AuthInfo)
    suspend fun getAuthInfo(): AuthInfo
}