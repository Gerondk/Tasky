package com.gkp.core.network

import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.domain.util.TaskyResult
import com.gkp.core.network.model.RefreshTokenBody
import com.gkp.core.network.util.networkApiCall
import kotlinx.coroutines.flow.Flow
import okhttp3.Response
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

class AccessTokenRefreshHandler(
    private val sessionStorage: SessionStorage,
    private val taskyRetrofitApi: TaskyRetrofitApi,
) {

    fun refreshAccessToken(): Flow<TaskyResult<Unit>> {
       return networkApiCall {
            val authInfo = sessionStorage.getAuthInfo()
            val refreshTokenResponse = taskyRetrofitApi.refreshToken(
                refreshTokenBody = RefreshTokenBody(
                    refreshToken = authInfo.refreshToken,
                    userId = authInfo.userId
                )
            )
            sessionStorage.saveAuthInfo(
                authInfo.copy(
                    accessToken = refreshTokenResponse.accessToken,
                    accessTokenExpirationTimestamp = refreshTokenResponse.accessTokenExpirationTimestamp
                )
            )
        }
    }

    fun shouldRefreshAccessToken(response: Response): Boolean {
        val authUrlPathSegments = listOf("login", "register","logout")
        return with(response) {
            code == HTTP_UNAUTHORIZED &&
                    request.url.pathSegments.intersect(authUrlPathSegments).isEmpty()
        }
    }
}
