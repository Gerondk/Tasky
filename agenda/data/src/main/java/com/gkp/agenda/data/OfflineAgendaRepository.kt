package com.gkp.agenda.data

import com.gkp.agenda.domain.AgendaRepository
import com.gkp.agenda.domain.datasource.LocalAgendaDataSource
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.domain.model.AgendaItemType
import com.gkp.agenda.domain.sync.SyncAgendaItems
import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.database.dao.PendingSyncCreatedAgendaItemsDao
import com.gkp.core.database.dao.PendingSyncDeletedAgendaItemsDao
import com.gkp.core.database.dao.PendingSyncUpdatedAgendaItemsDao
import com.gkp.core.database.entity.DeletedAgendaItemEntity
import com.gkp.core.database.entity.UpdatedAgendaItemEntity
import com.gkp.core.database.mapper.toAgendaItemEntity
import com.gkp.core.network.TaskyRetrofitApi
import com.gkp.core.network.model.buildEventBodyPart
import com.gkp.core.network.model.buildPhotosPart
import com.gkp.core.network.util.networkApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class OfflineAgendaRepository(
    private val taskyRetrofitApi: TaskyRetrofitApi,
    private val sessionStorage: SessionStorage,
    private val localAgendaDataSource: LocalAgendaDataSource,
    private val scope: CoroutineScope,
    private val syncAgendaItems: SyncAgendaItems,
    private val pendingSyncUpdatedAgendaItemsDao: PendingSyncUpdatedAgendaItemsDao,
    private val pendingSyncDeletedAgendaItemsDao: PendingSyncDeletedAgendaItemsDao,
    private val pendingSyncCreatedAgendaItemsDao: PendingSyncCreatedAgendaItemsDao
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
                    agendaItem = agendaItem,
                    userId = sessionStorage.getAuthInfo().userId
                )
                syncAgendaItems.syncCreatedAgendaItem(
                    agendaItemId = agendaItem.id,
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
        }.onEach {
            val error = it.getErrorOrNull()
            error?.let {
                pendingSyncUpdatedAgendaItemsDao.upsertUpdatedItem(
                    UpdatedAgendaItemEntity(
                        agendaItemId = agendaItem.id,
                        userId = sessionStorage.getAuthInfo().userId,
                        agendaItem = agendaItem.toAgendaItemEntity()
                    )
                )
                syncAgendaItems.syncUpdatedAgendaItem(
                    agendaItemId = agendaItem.id,
                )
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
                pendingSyncDeletedAgendaItemsDao.upsert(
                    DeletedAgendaItemEntity(
                        id = agendaItem.id,
                        userId = sessionStorage.getAuthInfo().userId,
                        agendaItemType = agendaItemType.name
                    )
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

    override suspend fun pushOfflineAgendaItems() {
        val userId = sessionStorage.getAuthInfo().userId
        val offlineDeletedAgendaItems = pendingSyncDeletedAgendaItemsDao.getAllDeletedAgendaItemsForUser(userId)

        val deletedJobs = offlineDeletedAgendaItems.map { deletedAgendaItem ->
            scope.launch {
                syncAgendaItems.syncDeletedAgendaItem(
                    agendaItemId = deletedAgendaItem.id,
                    agendaItemType = AgendaItemType.valueOf(deletedAgendaItem.agendaItemType)
                )
            }
        }

        val offlineUpdatedAgendaItems = pendingSyncUpdatedAgendaItemsDao.getUpdatedItemsByUserId(userId)
        val updatedJobs = offlineUpdatedAgendaItems.map { updatedAgendaItem ->
            scope.launch {
                syncAgendaItems.syncUpdatedAgendaItem(
                    agendaItemId = updatedAgendaItem.agendaItemId,
                )
            }
        }

        val offlineCreatedAgendaItems = pendingSyncCreatedAgendaItemsDao.getCreatedAgendaItemsByUserId(userId)
        val createdJobs = offlineCreatedAgendaItems.map { createdAgendaItem ->
            scope.launch {
                syncAgendaItems.syncCreatedAgendaItem(
                    agendaItemId = createdAgendaItem.agendaItemId,
                )
            }
        }

        updatedJobs.joinAll()
        createdJobs.joinAll()
        deletedJobs.joinAll()
        fetchAgendaItems()
    }

    override suspend fun periodicalSyncAgendaItems() {
        syncAgendaItems.syncFullAgenda()
    }

    override suspend fun logout() {
        networkApiCall {
            syncAgendaItems.cancelWorkers()
            localAgendaDataSource.deleteAllAgendaItems()
            taskyRetrofitApi.logout()
        }.launchIn(scope).join()
        scope.launch {
            sessionStorage.resetAuthInfo()
        }.join()
    }
}