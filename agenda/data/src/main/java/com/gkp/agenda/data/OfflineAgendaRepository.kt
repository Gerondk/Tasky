package com.gkp.agenda.data

import android.annotation.SuppressLint
import com.gkp.agenda.domain.AgendaRepository
import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.domain.util.TaskyResult
import com.gkp.core.network.TaskyRetrofitApi
import com.gkp.core.network.util.networkApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class OfflineAgendaRepository(
    private val taskyRetrofitApi: TaskyRetrofitApi,
    private val sessionStorage: SessionStorage,
) : AgendaRepository {
    private val scope = CoroutineScope(SupervisorJob())

    override suspend fun getAgenda(time: Long) {

    }

    @SuppressLint("SuspiciousIndentation")
    override  fun logout() {
        val job = networkApiCall {
            taskyRetrofitApi.logout()
            sessionStorage.resetAuthInfo()
        }.launchIn(scope)
        scope.launch {
            job.join()
        }
    }
}