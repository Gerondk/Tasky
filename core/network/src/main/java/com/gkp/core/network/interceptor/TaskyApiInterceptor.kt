package com.gkp.core.network.interceptor

import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.network.AccessTokenRefreshHandler
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TaskyApiInterceptor(
    private val accessTokenRefreshHandler: AccessTokenRefreshHandler,
    private val sessionStorage: SessionStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder().apply {
            applyApiKey()
            val accessToken = runBlocking {
                sessionStorage.getAuthInfo().accessToken
            }
            if (accessToken.isNotEmpty()) {
                applyAuthorization(accessToken)
            }
        }
        val response = chain.proceed(requestBuilder.build())
        if (accessTokenRefreshHandler.shouldRefreshAccessToken(response)) {
            runBlocking {
                accessTokenRefreshHandler.refreshAccessToken().launchIn(this)
            }
        }
        return response
    }
}

