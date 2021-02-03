package com.why.kotlin.coroutines.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.why.kotlin.coroutines.theme.MyTheme
import com.why.kotlin.coroutines.viewnodels.MainViewModel
import com.why.kotlin.coroutines.workers.RefreshTitleWorker
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Periodic work has a minimum interval of 15 minutes.
        val work = PeriodicWorkRequestBuilder<RefreshTitleWorker>(
            15, TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(this).enqueue(work)

        setContent {
            val mainViewModel = viewModel<MainViewModel>()

            MyTheme {
                Home(mainViewModel)
            }
        }
    }
}
