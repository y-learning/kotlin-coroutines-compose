package com.why.kotlin.coroutines.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.why.kotlin.coroutines.app.Result.ERROR
import com.why.kotlin.coroutines.app.Result.OK
import com.why.kotlin.coroutines.app.TitleGateway
import com.why.kotlin.coroutines.app.TitleGatewayImp

public class RefreshTitleWorker(
    context: Context,
    params: WorkerParameters,
    private val gateway: TitleGateway
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val result = gateway.getTitle()
        return when {
            result[ERROR] != null -> {
                Log.println(Log.ERROR, "GATEWAY", "${result[OK]}")
                Result.failure()
            }
            else -> {
                Log.println(Log.INFO, "GATEWAY", "${result[OK]}")
                Result.success()
            }
        }
    }

    class Factory(
        val gateway: TitleGateway = TitleGatewayImp()
    ) : WorkerFactory() {

        override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshTitleWorker(appContext, workerParameters, gateway)
        }
    }
}
