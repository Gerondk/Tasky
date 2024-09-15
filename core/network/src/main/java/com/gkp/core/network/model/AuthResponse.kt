package com.gkp.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse (
    val accessToken: String,
    val refreshToken: String,
    val fullName: String,
    val userId: String,
    val accessTokenExpirationTimestamp: Long,
)

@Serializable
data class RefreshTokenResponse(
    val accessToken: String,
    val accessTokenExpirationTimestamp: Long
)

