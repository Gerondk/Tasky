package com.gkp.agenda.data

import com.gkp.agenda.domain.AgendaRepository
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.domain.util.TaskyResult
import com.gkp.core.network.TaskyRetrofitApi
import com.gkp.core.network.model.buildEventBodyPart
import com.gkp.core.network.model.buildPhotosPart
import com.gkp.core.network.util.networkApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn

class OfflineAgendaRepository(
    private val taskyRetrofitApi: TaskyRetrofitApi,
    private val sessionStorage: SessionStorage,
    private val scope: CoroutineScope,
) : AgendaRepository {

    override fun fetchAgendaItems(time: Long): Flow<TaskyResult<List<AgendaItem>>> {
        return networkApiCall {
            taskyRetrofitApi.getAgenda(time).toAgendaItems()
        }
    }

    override fun addAgendaItem(agendaItem: AgendaItem) {
        when (agendaItem) {
            is AgendaItem.Reminder -> {
                networkApiCall {
                    taskyRetrofitApi.createReminder(agendaItem.toReminderBody())
                }
            }
            is AgendaItem.Task -> {
                networkApiCall {
                    taskyRetrofitApi.createTask(agendaItem.toTaskBody())
                }
            }
            is AgendaItem.Event -> {
                networkApiCall {
                    val eventBody = agendaItem.toEventBody()
                    val eventBodyPart = buildEventBodyPart(eventBody)
                    val photosPart = buildPhotosPart(agendaItem.photos)
                    taskyRetrofitApi.createEvent(
                        eventBodyPart = eventBodyPart,
                        photosPart = photosPart
                    )
                }
            }
        }.launchIn(scope)
    }

    override fun updateAgendaItem(agendaItem: AgendaItem) {
        when(agendaItem){
            is AgendaItem.Event -> {
                networkApiCall {
                    val eventBody = agendaItem.toEventBody()
                    val eventBodyPart = buildEventBodyPart(eventBody)
                    val photosPart = buildPhotosPart(agendaItem.photos)
                    taskyRetrofitApi.updateEvent(
                        eventBodyPart = eventBodyPart,
                        photosPart = photosPart
                    )
                }

            }
            is AgendaItem.Reminder -> {
                networkApiCall {
                    taskyRetrofitApi.updateReminder(agendaItem.toReminderBody())
                }
            }
            is AgendaItem.Task -> {
                networkApiCall {
                    taskyRetrofitApi.updateTask(agendaItem.toTaskBody())
                }
            }
        }.launchIn(scope)
    }

    override fun deleteAgendaItem(agendaItem: AgendaItem) {
        when(agendaItem){
            is AgendaItem.Event -> {
                networkApiCall {
                    taskyRetrofitApi.deleteEvent(agendaItem.id)
                }
            }

            is AgendaItem.Reminder -> {
                networkApiCall {
                    taskyRetrofitApi.deleteReminder(agendaItem.id)
                }
            }
            is AgendaItem.Task -> {
                networkApiCall {
                    taskyRetrofitApi.deleteTask(agendaItem.id)
                }

            }
        }.launchIn(scope)
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

    override fun fetchAgendaItemEvent(id: String): Flow<TaskyResult<AgendaItem.Event>> {
        return networkApiCall {
            taskyRetrofitApi.getEvent(id).toAgendaEventItem()
        }
    }


    override fun logout() {
        networkApiCall {
            sessionStorage.resetAuthInfo()
            taskyRetrofitApi.logout()
        }.launchIn(scope)
    }
}