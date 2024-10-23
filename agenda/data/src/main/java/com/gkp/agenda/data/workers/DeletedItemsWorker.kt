package com.gkp.agenda.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.gkp.agenda.domain.model.AgendaItemType
import com.gkp.core.database.dao.DeletedAgendaItemsDao
import com.gkp.core.network.TaskyRetrofitApi
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

class DeletedItemsWorker(
    private val taskyRetrofitApi: TaskyRetrofitApi,
    private val deletedAgendaItemsDao: DeletedAgendaItemsDao,
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val agendaItemId = inputData.getString(AGENDA_ITEM_ID) ?: return Result.failure()
        val agendaItemType = inputData.getString(AGENDA_ITEM_TYPE) ?: return Result.failure()
        val type = AgendaItemType.valueOf(agendaItemType)
       if(runAttemptCount > 3 ){
           return Result.failure()
       }
       return try {
           performDelete(agendaItemId, type)
           deletedAgendaItemsDao.deleteForId(agendaItemId)
           Result.success()
       } catch (e: Exception) {
           currentCoroutineContext().ensureActive()
           Result.failure()
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