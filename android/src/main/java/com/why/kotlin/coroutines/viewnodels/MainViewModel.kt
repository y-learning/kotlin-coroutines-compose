package com.why.kotlin.coroutines.viewnodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var topAppBarTitle by mutableStateOf("Kotlin Coroutines")
        private set

    var itemTitle by mutableStateOf("Title")
        private set

    var tapsCount by mutableStateOf(0)
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun incTaps() {
        tapsCount++
    }

    fun loadingOn() {
        isLoading = true
    }

    fun loadingOff() {
        isLoading = false
    }
}
