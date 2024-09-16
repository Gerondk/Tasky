package com.gkp.auth.data

import com.gkp.auth.domain.AuthInfo
import com.gkp.core.network.model.LoginResponse

fun LoginResponse.toAuthInfo() =
    AuthInfo(
        accessToken = accessToken,
        refreshToken = refreshToken,
        fullName = fullName,
        userId =  userId,
        accessTokenExpirationTimestamp = accessTokenExpirationTimestamp
    )
fun AuthInfo.toLoginResponse() =
    LoginResponse(
        accessToken = accessToken,
        refreshToken = refreshToken,
        fullName = fullName,
        userId =  userId,
        accessTokenExpirationTimestamp = accessTokenExpirationTimestamp
    )