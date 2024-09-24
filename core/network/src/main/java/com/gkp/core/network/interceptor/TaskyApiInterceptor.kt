package com.gkp.core.network.interceptor

import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.network.AccessTokenRefreshHandler
import com.gkp.network.BuildConfig
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TaskyApiInterceptor(
    private val accessTokenRefreshHandler: AccessTokenRefreshHandler,
    private val sessionStorage: SessionStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuider = chain.request().newBuilder()
        requestBuider.applyApiKey()

         val accessToken = runBlocking {
             sessionStorage.getAuthInfo().accessToken
         }
        if (accessToken.isNotEmpty()) {
            requestBuider.applyAuthorization(accessToken)
        }
        val response = chain.proceed(requestBuider.build())
        if (accessTokenRefreshHandler.shouldRefreshAccessToken(response)) {
            runBlocking {
                accessTokenRefreshHandler.refreshAccessToken().launchIn(this)
            }
        }
        return response
    }
}

