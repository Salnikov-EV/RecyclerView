package otus.gpb.recyclerview

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json

class ChatApiService {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
            )
        }
        defaultRequest {
            header("Content-Type", "application/json")
        }
    }

    @OptIn(InternalSerializationApi::class)
    suspend fun getChatList() : List<ChatItem> {
        return client.get("https://storage.yandexcloud.net/recycle-view/rv.json").body()
    }

    fun close() {
        client.close()
    }
}