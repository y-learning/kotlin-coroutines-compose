package com.why.kotlin.coroutines.viewnodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.why.kotlin.coroutines.app.Result
import com.why.kotlin.coroutines.app.Result.ERROR
import com.why.kotlin.coroutines.app.Result.OK
import com.why.kotlin.coroutines.app.TitleGateway
import com.why.kotlin.coroutines.app.TitleGatewayImp
import kotlinx.coroutines.launch

class MainViewModel(private val gateway: TitleGateway = TitleGatewayImp()) :
    ViewModel() {

    var isError: Boolean by mutableStateOf(false)
        private set

    var topAppBarTitle by mutableStateOf("Kotlin Coroutines")
        private set

    private fun designTimeTitle() = "Title Title Title Title Title Title " +
            "Title Title Title Title Title Title Title Title Title Title " +
            "Title Title Title Title "

    var itemTitle by mutableStateOf(designTimeTitle())
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

    private fun incTaps() {
        tapsCount++
    }

    private fun showError() {
        isError = true
    }

    fun hideError() {
        isError = false
    }

    private fun isError(result: Map<Result, Any?>) =
        result[ERROR] as String? != null

    fun refreshTopBarTitle() {
        runWithLoadingIndicators {
            val result = gateway.getTitle()

            when {
                isError(result) -> showError()
                else -> {
                    itemTitle = result[OK] as String? ?: "No title found!"
                    incTaps()
                }
            }
        }
    }
}
