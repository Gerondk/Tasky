package com.gkp.auth.domain

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfo(
    val accessToken: String,
    val refreshToken: String,
    val fullName: String,
    val userId: String,
    val accessTokenExpirationTimestamp: Long
)

data class RefreshTokenInfo(
    val accessToken: String,
    val accessTokenExpirationTimestamp: Long
)

fun resetAuthInfo() = AuthInfo(
    accessToken = "",
    refreshToken = "",
    fullName = "",
    userId = "",
    accessTokenExpirationTimestamp = 0L
)
