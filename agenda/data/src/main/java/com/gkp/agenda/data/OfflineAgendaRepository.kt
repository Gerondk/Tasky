package com.gkp.agenda.data

import com.gkp.agenda.domain.AgendaRepository
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.domain.util.TaskyResult
import com.gkp.core.network.TaskyRetrofitApi
import com.gkp.core.network.util.networkApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn

class OfflineAgendaRepository(
    private val taskyRetrofitApi: TaskyRetrofitApi,
    private val sessionStorage: SessionStorage,
    private val scope: CoroutineScope,
) : AgendaRepository {

    override fun fetchAgendaItems(time: Long) : Flow<TaskyResult<List<AgendaItem>>> {
        return networkApiCall {
            taskyRetrofitApi.getAgenda(time).toAgendaItems()
        }
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

    override fun fetchAgendaItemTask(id: String): Flow<TaskyResult<AgendaItem.Task>> {
        return networkApiCall {
            taskyRetrofitApi.getTask(id).toAgendaTaskItem()
        }
    }

    override fun fetchAgendaItemReminder(id: String): Flow<TaskyResult<AgendaItem.Reminder>> {
        return networkApiCall {
            taskyRetrofitApi.getReminder(id).toAgendaReminderItem()
        }
    }


    override fun logout() {
        networkApiCall {
            sessionStorage.resetAuthInfo()
            taskyRetrofitApi.logout()
        }.launchIn(scope)
    }
}