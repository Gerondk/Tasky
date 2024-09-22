package com.gkp.core.network.interceptor

import com.gkp.core.network.AccessTokenRefreshHandler
import com.gkp.network.BuildConfig
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TaskyApiInterceptor(
    private val accessTokenRefreshHandler: AccessTokenRefreshHandler,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(
                "x-api-key",
                BuildConfig.API_KEY
            ).build()

        val response = chain.proceed(request)
        if (accessTokenRefreshHandler.shouldRefreshAccessToken(response)) {
            runBlocking {
                accessTokenRefreshHandler.refreshAccessToken().launchIn(this)
            }
        }
        return response
    }
}
