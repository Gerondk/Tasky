package com.gkp.core.network

import com.gkp.core.network.model.LoginBody
import com.gkp.core.network.model.LoginResponse
import com.gkp.core.network.model.RefreshTokenResponse
import com.gkp.core.network.model.RegisterBody
import retrofit2.http.Body
import retrofit2.http.POST

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
        refreshToken: String,
        userId: String
    ) : RefreshTokenResponse

}