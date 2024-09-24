package com.gkp.core.network.interceptor

import com.gkp.auth.domain.session.SessionStorage
import com.gkp.network.BuildConfig
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class TaskyRefreshTokenInterceptor(
    private val sessionStorage: SessionStorage
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking {
            sessionStorage.getAuthInfo().accessToken
        }
        val requestBuider = chain.request()
            .newBuilder()
            .applyApiKey()
            .applyAuthorization(accessToken)

        return chain.proceed(requestBuider.build())
    }
}