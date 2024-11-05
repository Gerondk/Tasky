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
import com.gkp.auth.domain.session.SessionStorage
import com.gkp.core.database.dao.PendingSyncUpdatedAgendaItemsDao
import com.gkp.core.database.mapper.toAgendaItem
import com.gkp.core.network.TaskyRetrofitApi
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

class UpdatedItemsWorker(
    private val taskyRetrofitApi: TaskyRetrofitApi,
    private val pendingSyncUpdatedItemsDao: PendingSyncUpdatedAgendaItemsDao,
    private val sessionStorage: SessionStorage,
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        if (sessionStorage.getAuthInfo().accessToken.isBlank()){
            return Result.failure()
        }
        if ( runAttemptCount >= 5) {
            return Result.failure()

        }
        val agendaItemId = inputData.getString(AGENDA_ITEM_ID) ?: return Result.failure()
        return try {
            val agendaItemEntity = pendingSyncUpdatedItemsDao.getUpdatedItemById(agendaItemId) ?: return Result.failure()
            performedRemoteUpdate(agendaItemEntity.agendaItem.toAgendaItem())
            pendingSyncUpdatedItemsDao.deleteById(agendaItemId)
            Result.success()
        } catch (e: Exception) {
            currentCoroutineContext().ensureActive()
            Result.retry()
        }
    }

    private suspend fun performedRemoteUpdate(agendaItem: AgendaItem) {
        when (agendaItem.getType()) {
            AgendaItemType.TASK -> {
                val task = agendaItem as AgendaItem.Task
                taskyRetrofitApi.updateTask(task.toTaskBody())
            }
            AgendaItemType.REMINDER -> {
                val reminder = agendaItem as AgendaItem.Reminder
                taskyRetrofitApi.updateReminder(reminder.toReminderBody())
            }
            AgendaItemType.EVENT -> {
                // TODO: handle images need to implemented
                val event = agendaItem as AgendaItem.Event
            }
        }
    }
}