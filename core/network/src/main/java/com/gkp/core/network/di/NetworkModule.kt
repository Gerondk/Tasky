package com.gkp.core.network.di

import com.gkp.core.network.AccessTokenRefreshHandler
import com.gkp.core.network.interceptor.TaskyApiInterceptor
import com.gkp.core.network.TaskyRetrofitApi
import com.gkp.core.network.interceptor.TaskyRefreshTokenInterceptor
import com.gkp.network.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit

const private val TASKY_API_REFRESH_TOKEN = "TASKY_API_REFRESH_TOKEN"

val networkModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(TaskyApiInterceptor(get()))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(TaskyRetrofitApi::class.java)
    }

    single(qualifier = qualifier(TASKY_API_REFRESH_TOKEN)) {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(TaskyRefreshTokenInterceptor())
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(TaskyRetrofitApi::class.java)

    }
    single {
        AccessTokenRefreshHandler(
            get(),
            get(qualifier = qualifier(TASKY_API_REFRESH_TOKEN)),
        )
    }
}

