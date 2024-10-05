package com.gkp.core.network

import com.gkp.core.network.model.LoginBody
import com.gkp.core.network.model.LoginResponse
import com.gkp.core.network.model.RefreshTokenBody
import com.gkp.core.network.model.RefreshTokenResponse
import com.gkp.core.network.model.RegisterBody
import com.gkp.core.network.model.TaskBody
import com.gkp.network.BuildConfig
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TaskyRetrofitApi {
    @POST("/register")
    suspend fun register(
       @Body registerBody: RegisterBody
    )

    @POST("/login")
    suspend fun login(
        @Body loginBody: LoginBody
    ) : LoginResponse

    @POST("/accessToken")
    suspend fun refreshToken(
        @Body refreshTokenBody: RefreshTokenBody,
    ) : RefreshTokenResponse

    @GET("/logout")
    suspend fun logout()

    @GET("/agenda")
    suspend fun getAgenda(
        @Query("time") time: Long = System.currentTimeMillis()
    )

    @POST("/task")
    suspend fun createTask(
        @Body taskBody: TaskBody
    )

}