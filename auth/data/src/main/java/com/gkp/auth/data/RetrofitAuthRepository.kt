package com.gkp.auth.data

import com.gkp.auth.domain.AuthRepository
import com.gkp.core.domain.util.TaskyResult
import com.gkp.core.domain.util.networkApiCall
import com.gkp.core.network.TaskyRetrofitApi
import com.gkp.core.network.model.LoginBody
import com.gkp.core.network.model.RegisterBody
import kotlinx.coroutines.flow.Flow

class RetrofitAuthRepository(private val taskyApi: TaskyRetrofitApi) : AuthRepository {
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
            // TODO: persist user auth in datastore
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