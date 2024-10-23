package com.gkp.agenda.data

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.await
import com.gkp.agenda.data.workers.AGENDA_ITEM_ID
import com.gkp.agenda.data.workers.AGENDA_ITEM_TYPE
import com.gkp.agenda.data.workers.CreatedItemsWorker
import com.gkp.agenda.data.workers.DeletedItemsWorker
import com.gkp.agenda.domain.model.AgendaItemType
import com.gkp.agenda.domain.sync.SyncAgendaItems
import java.util.concurrent.TimeUnit

class WorkerSync(
    context: Context,
) : SyncAgendaItems {

    private val workManager = WorkManager.getInstance(context)

    override suspend fun syncDeletedAgendaItem(
        agendaItemId: String,
        agendaItemType: AgendaItemType,
    ) {
        val request = OneTimeWorkRequestBuilder<DeletedItemsWorker>().setConstraints(
            constraints = Constraints.Builder().setRequiredNetworkType(
                NetworkType.CONNECTED
            ).build()

        ).setInputData(
            Data.Builder().putString(
                AGENDA_ITEM_ID,
                agendaItemId
            ).putString(
                AGENDA_ITEM_TYPE,
                agendaItemType.name
            ).build()
        ).setBackoffCriteria(
            backoffPolicy = BackoffPolicy.EXPONENTIAL,
            backoffDelay = 2000L,
            timeUnit = TimeUnit.MILLISECONDS
        ).build()

        workManager.enqueue(request).await()
    }

    override suspend fun syncCreatedAgendaItem(
        agendaItemId: String,
        agendaItemType: AgendaItemType,
    ) {
        val request = OneTimeWorkRequestBuilder<CreatedItemsWorker>()
            .setConstraints(
                constraints = Constraints.Builder().setRequiredNetworkType(
                    NetworkType.CONNECTED
                ).build()
            )
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.EXPONENTIAL,
                backoffDelay = 2000L,
                timeUnit = TimeUnit.MILLISECONDS
            )
            .setInputData(
                Data.Builder().putString(
                    AGENDA_ITEM_ID,
                    agendaItemId
                ).putString(
                    AGENDA_ITEM_TYPE,
                    agendaItemType.name
                ).build()
            ).build()

        workManager.enqueue(request).await()
    }
}