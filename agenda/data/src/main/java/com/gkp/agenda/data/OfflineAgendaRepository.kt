package com.gkp.agenda.data

import com.gkp.agenda.domain.AgendaRepository
import com.gkp.agenda.domain.datasource.LocalAgendaDataSource
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.domain.model.AgendaItemType
import com.gkp.agenda.domain.sync.SyncAgendaItems
import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.network.TaskyRetrofitApi
import com.gkp.core.network.model.buildEventBodyPart
import com.gkp.core.network.model.buildPhotosPart
import com.gkp.core.network.util.networkApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class OfflineAgendaRepository(
    private val taskyRetrofitApi: TaskyRetrofitApi,
    private val sessionStorage: SessionStorage,
    private val localAgendaDataSource: LocalAgendaDataSource,
    private val scope: CoroutineScope,
    private val syncAgendaItems: SyncAgendaItems
) : AgendaRepository {

    override fun getAgendaItemsForDate(dateLong: Long): Flow<List<AgendaItem>> {
        return localAgendaDataSource.getAgendaItemsForDate(dateLong)
    }

    override suspend fun fetchAgendaItems() {
        networkApiCall {
            val agendaItems = taskyRetrofitApi.getFullAgenda().toAgendaItems()
            localAgendaDataSource.deleteAllAgendaItems()
            localAgendaDataSource.updateAgendaItems(agendaItems)
        }.launchIn(scope).join()
    }

    override suspend fun addAgendaItem(agendaItem: AgendaItem) {
        val localAddJob = scope.launch {
            localAgendaDataSource.addOrSaveAgendaItem(agendaItem)
        }

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
        }.onEach {
            val error = it.getErrorOrNull()
            error?.let {
                localAgendaDataSource.saveCreatedAgendaItem(
                    agendaItemId = agendaItem.id,
                    userId = sessionStorage.getAuthInfo().userId
                )
                syncAgendaItems.syncCreatedAgendaItem(
                    agendaItemId = agendaItem.id,
                    agendaItemType = agendaItem.getType()
                )
            }

        }.launchIn(scope)

        localAddJob.join()
    }

    override suspend fun updateAgendaItem(agendaItem: AgendaItem) {
        val localUpdateJob = scope.launch {
            localAgendaDataSource.addOrSaveAgendaItem(agendaItem)
        }

        when (agendaItem) {
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

        localUpdateJob.join()
    }

    override suspend fun deleteAgendaItem(agendaItem: AgendaItem) {
        val localDeleteJob = scope.launch {
            localAgendaDataSource.deleteAgendaItemById(agendaItem.id)
        }
        val agendaItemType = agendaItem.getType()

        when (agendaItem) {
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
        }.onEach {
            val error = it.getErrorOrNull()
            error?.let {
                localAgendaDataSource.saveAgendaDeletedItem(
                    agendaItemId = agendaItem.id,
                    userId = sessionStorage.getAuthInfo().userId
                )
                syncAgendaItems.syncDeletedAgendaItem(
                    agendaItemId = agendaItem.id,
                    agendaItemType = agendaItemType
                )
            }
        }.launchIn(scope)

        localDeleteJob.join()
    }

    override suspend fun getAgendaItemForId(id: String): AgendaItem {
        return localAgendaDataSource.getAgendaItemById(id)
    }

    override fun logout() {
        networkApiCall {
            sessionStorage.resetAuthInfo()
            taskyRetrofitApi.logout()
        }.launchIn(scope)
    }
}