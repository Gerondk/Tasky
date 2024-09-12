package com.gkp.core.network

import com.gkp.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TaskyApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(
                "x-api-key",
                BuildConfig.API_KEY
            ).build()

        return chain.proceed(request)
    }
}