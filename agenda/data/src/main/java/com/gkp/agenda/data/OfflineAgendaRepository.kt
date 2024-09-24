package com.gkp.agenda.data

import com.gkp.agenda.domain.AgendaRepository
import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.network.TaskyRetrofitApi
import com.gkp.core.network.util.networkApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn

class OfflineAgendaRepository(
    private val taskyRetrofitApi: TaskyRetrofitApi,
    private val sessionStorage: SessionStorage,
    private val scope: CoroutineScope,
) : AgendaRepository {

    override suspend fun getAgenda(time: Long) {

    }

    override fun logout() {
        networkApiCall {
            taskyRetrofitApi.logout()
            sessionStorage.resetAuthInfo()
        }.launchIn(scope)
    }
}