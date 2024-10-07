package com.gkp.agenda.data

import com.gkp.agenda.domain.AgendaRepository
import com.gkp.agenda.domain.model.AgendaItem
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

    override fun addAgendaItem(agendaItem: AgendaItem) {
        when (agendaItem) {
            is AgendaItem.Reminder -> {
                networkApiCall {
                    taskyRetrofitApi.createReminder(agendaItem.toReminderBody())
                }.launchIn(scope)
            }

            is AgendaItem.Task -> {
                networkApiCall {
                    taskyRetrofitApi.createTask(agendaItem.toTaskBody())
                }.launchIn(scope)
            }
        }
    }


    override fun logout() {
        networkApiCall {
            sessionStorage.resetAuthInfo()
            taskyRetrofitApi.logout()
        }.launchIn(scope)
    }
}