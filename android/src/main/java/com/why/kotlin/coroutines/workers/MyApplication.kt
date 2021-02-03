package com.why.kotlin.coroutines.workers

import android.app.Application
import androidx.work.Configuration

class MyApplication : Application(), Configuration.Provider {
    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .setWorkerFactory(RefreshTitleWorker.Factory())
            .build()
}