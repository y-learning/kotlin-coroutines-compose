package com.why.kotlin.coroutines.app

enum class Result {
    OK,
    ERROR
}

interface TitleGateway {
    suspend fun getTitle(): Map<Result, Any?>
}
