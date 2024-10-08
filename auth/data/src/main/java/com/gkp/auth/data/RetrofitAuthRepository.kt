package com.gkp.auth.data

import com.gkp.auth.domain.AuthRepository
import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.domain.util.TaskyResult

import com.gkp.core.network.TaskyRetrofitApi
import com.gkp.core.network.model.LoginBody
import com.gkp.core.network.model.RegisterBody
import com.gkp.core.network.util.networkApiCall
import kotlinx.coroutines.flow.Flow

class RetrofitAuthRepository(
    private val taskyApi: TaskyRetrofitApi,
    private val sessionStorage: SessionStorage
) : AuthRepository {
    override fun login(
        email: String,
        password: String,
    ): Flow<TaskyResult<Unit>> {
        return networkApiCall {
            val authInfo = taskyApi.login(
                LoginBody(
                    email = email,
                    password = password
                )
            ).toAuthInfo()
            sessionStorage.saveAuthInfo(authInfo)
        }
    }

    override fun register(
        fullName: String,
        email: String,
        password: String,
    ): Flow<TaskyResult<Unit>> {
        return networkApiCall {
            taskyApi.register(
                RegisterBody(
                    fullName = fullName,
                    email = email,
                    password = password
                )
            )
        }
    }
}