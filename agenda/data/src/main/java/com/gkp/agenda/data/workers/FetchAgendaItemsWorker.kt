package com.gkp.agenda.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.gkp.agenda.domain.AgendaRepository
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

class FetchAgendaItemsWorker(
    private val agendaRepository: AgendaRepository,
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params){
    override suspend fun doWork(): Result {
        if (runAttemptCount > 5) {
            return Result.failure()
        }
        return try {
            agendaRepository.fetchAgendaItems()
            Result.success()
        }catch (e: Exception) {
            currentCoroutineContext().ensureActive()
            Result.retry()
        }
    }
}