package com.gkp.core.network.interceptor

import com.gkp.network.BuildConfig
import okhttp3.Request

fun Request.Builder.applyApiKey(): Request.Builder {
    return applyHeader(
        name = "x-api-key",
        value = BuildConfig.API_KEY
    )
}

fun Request.Builder.applyAuthorization(
    accessToken: String
): Request.Builder {
    return applyHeader(
        name = "Authorization",
        value = "Bearer $accessToken"
    )
}
private fun Request.Builder.applyHeader(
    name: String,
    value: String
): Request.Builder {
    return this.apply {
        addHeader(name, value)
    }
}