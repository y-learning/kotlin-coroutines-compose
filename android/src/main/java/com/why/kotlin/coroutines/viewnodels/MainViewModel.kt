package com.why.kotlin.coroutines.viewnodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var topAppBarTitle by mutableStateOf("Kotlin Coroutines")
        private set

    var itemTitle by mutableStateOf("Title")
        private set

    var tapsCount by mutableStateOf(0)
        private set

    var isLoading by mutableStateOf(false)
        private set

    private fun loadingOn() {
        isLoading = true
    }

    private fun loadingOff() {
        isLoading = false
    }

    private fun runWithLoadingIndicators(action: suspend () -> Unit) {
        viewModelScope.launch {
            loadingOn()

            action()

            loadingOff()
        }
    }

    private suspend fun slowIncTaps() {
        delay(1_000)
        tapsCount++
    }

    fun incTaps() {
        runWithLoadingIndicators {
            slowIncTaps()
        }
    }
}
