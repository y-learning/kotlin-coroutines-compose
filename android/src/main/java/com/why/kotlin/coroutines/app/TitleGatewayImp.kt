package com.why.kotlin.coroutines.app

import com.why.kotlin.coroutines.app.Result.ERROR
import com.why.kotlin.coroutines.app.Result.OK
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlin.random.Random

data class Repo(
    val id: Int,
    val description: String?
)

data class Repos(
    val totalCount: Int,
    val items: List<Repo>
)


class TitleGatewayImp : TitleGateway {
    private val baseUrl = "https://api.github.com"

    private fun httpClient() = HttpClient(Android) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    private fun searchByStarsCountUrl(starsCount: Int) =
        "$baseUrl/search/repositories?q=stars:$starsCount&type=Repositories"

    override suspend fun getTitle(): Map<Result, Any?> = httpClient().use {
        val url = searchByStarsCountUrl(Random.nextInt(0, 1000))

        val result: Map<Result, Any?> = try {
            mapOf(OK to it.get<Repos>(url).items[0].description)
        } catch (e: ClientRequestException) {
            mapOf(ERROR to e.response.status.description)
        }

        result
    }
}
