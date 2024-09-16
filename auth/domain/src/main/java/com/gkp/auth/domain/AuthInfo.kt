package com.gkp.auth.domain


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

