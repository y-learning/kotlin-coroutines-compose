package com.why.kotlin.coroutines.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import com.why.kotlin.coroutines.theme.MyTheme
import com.why.kotlin.coroutines.viewnodels.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel = viewModel<MainViewModel>()

            MyTheme {
                Home(mainViewModel)
            }
        }
    }
}
