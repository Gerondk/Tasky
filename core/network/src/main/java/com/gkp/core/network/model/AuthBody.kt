package com.gkp.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(
    val email: String,
    val password: String
)

@Serializable
data class RegisterBody(
    val fullName: String,
    val email: String,
    val password: String
)

@Serializable
data class RefreshTokenBody(
    val refreshToken: String,
    val userId: String
)