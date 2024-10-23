package com.gkp.tasky

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.gkp.tasky.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class TaskyApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@TaskyApplication)
            modules(
                appModule
            )
            workManagerFactory()
        }
    }
}