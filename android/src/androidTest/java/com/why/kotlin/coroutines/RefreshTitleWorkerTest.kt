package com.why.kotlin.coroutines

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import com.why.kotlin.coroutines.app.Result
import com.why.kotlin.coroutines.app.TitleGateway
import com.why.kotlin.coroutines.app.TitleGatewayImp
import com.why.kotlin.coroutines.workers.RefreshTitleWorker
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf


class FakeTitleGateway : TitleGateway {

    override suspend fun getTitle(): Map<Result, Any?> {
        return mapOf()
    }
}

class RefreshTitleWorkerTest : FreeSpec(
    {
        "Factory" {
            val factory = RefreshTitleWorker.Factory()
            factory.gateway.shouldBeInstanceOf<TitleGatewayImp>()
        }

        "start()" {
            val ctx = ApplicationProvider.getApplicationContext<Context>()
            val worker = TestListenableWorkerBuilder<RefreshTitleWorker>(ctx)
                .setWorkerFactory(
                    RefreshTitleWorker.Factory(FakeTitleGateway())
                )
                .build()

            // Start the work synchronously
            val result = worker.startWork().get()

            result shouldBe ListenableWorker.Result.success()
        }
    }
)
