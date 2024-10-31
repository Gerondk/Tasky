package com.gkp.agenda.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.gkp.agenda.domain.model.AgendaItemType
import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.database.dao.PendingSyncDeletedAgendaItemsDao
import com.gkp.core.network.TaskyRetrofitApi
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

class DeletedItemsWorker(
    private val taskyRetrofitApi: TaskyRetrofitApi,
    private val pendingSyncDeletedAgendaItemsDao: PendingSyncDeletedAgendaItemsDao,
    private val sessionStorage: SessionStorage,
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        if (sessionStorage.getAuthInfo().accessToken.isBlank()){
            return Result.failure()
        }
        if (runAttemptCount >= 5) {
            return Result.failure()
        }

        val agendaItemId = inputData.getString(AGENDA_ITEM_ID) ?: return Result.failure()
        val agendaItemType = inputData.getString(AGENDA_ITEM_TYPE) ?: return Result.failure()
        val type = AgendaItemType.valueOf(agendaItemType)
       return try {
           performDelete(agendaItemId, type)
           pendingSyncDeletedAgendaItemsDao.deleteForId(agendaItemId)
           Result.success()
       } catch (e: Exception) {
           currentCoroutineContext().ensureActive()
           Result.retry()
       }
    }

    private suspend fun performDelete(
        agendaItemId: String,
        agendaItemType: AgendaItemType,
    ) {
        when (agendaItemType) {
            AgendaItemType.TASK -> {
                taskyRetrofitApi.deleteTask(agendaItemId)
            }
            AgendaItemType.REMINDER -> {
                taskyRetrofitApi.deleteReminder(agendaItemId)
            }
            AgendaItemType.EVENT -> {
                taskyRetrofitApi.deleteEvent(agendaItemId)
            }
        }
    }

}