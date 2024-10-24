package com.gkp.agenda.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.gkp.agenda.data.getType
import com.gkp.agenda.data.toReminderBody
import com.gkp.agenda.data.toTaskBody
import com.gkp.agenda.domain.datasource.LocalAgendaDataSource
import com.gkp.agenda.domain.model.AgendaItem
import com.gkp.agenda.domain.model.AgendaItemType
import com.gkp.core.database.dao.CreatedAgendaItemsDao
import com.gkp.core.network.TaskyRetrofitApi
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

class CreatedItemsWorker(
    private val taskyRetrofitApi: TaskyRetrofitApi,
    private val localDataSource: LocalAgendaDataSource,
    private val createdAgendaItemsDao: CreatedAgendaItemsDao,
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        if (runAttemptCount >= 5) {
            return Result.failure()
        }
        val agendaItemId = inputData.getString(AGENDA_ITEM_ID) ?: return Result.failure()
        return try {
            val agendaItem = localDataSource.getAgendaItemById(agendaItemId)
            performPushCreatedRequest(agendaItem)
            createdAgendaItemsDao.deleteById(agendaItemId)

            Result.success()
        } catch (e: Exception) {
            currentCoroutineContext().ensureActive()
            Result.retry()
        }

    }

    private suspend fun performPushCreatedRequest(agendaItem: AgendaItem) {
        when (agendaItem.getType()) {
            AgendaItemType.TASK -> {
                val taskItem = agendaItem as AgendaItem.Task
                taskyRetrofitApi.createTask(taskItem.toTaskBody())
            }
            AgendaItemType.REMINDER -> {
                val reminderItem = agendaItem as AgendaItem.Reminder
                taskyRetrofitApi.createReminder(reminderItem.toReminderBody())
            }
            AgendaItemType.EVENT -> {
                // TODO: save in images in the db not done yet
                val eventItem = agendaItem as AgendaItem.Event
            }
        }
    }
}