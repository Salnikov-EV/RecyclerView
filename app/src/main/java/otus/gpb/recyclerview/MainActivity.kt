package otus.gpb.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi

class MainActivity : AppCompatActivity() {

    private lateinit var chatApiService: ChatApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chatApiService = ChatApiService()
        fetchChats()

    }

    override fun onDestroy() {
        super.onDestroy()
        chatApiService.close() // Закрываем клиент при уничтожении Activity
    }

    @OptIn(InternalSerializationApi::class)
    private fun fetchChats() {
        lifecycleScope.launch {
            try {
                val chats = chatApiService.getChatList()
                updateUI(chats)
            } catch (e: Exception) {
                handleError(e)
            } finally {
//                showLoading(false)
            }
        }
    }

    @OptIn(InternalSerializationApi::class)
    private fun updateUI(chats: List<ChatItem>) {
        // Обновление UI — например, заполнение RecyclerView
        chats.forEach { chat ->
            when(chat) {
                is ChatItem.UserItem -> Log.d("Ktor", "chat: ${chat.userName}, ${chat.message}")
                is ChatItem.GroupItem -> Log.d("Ktor", "chat: ${chat.userName}, ${chat.message}")
            }
        }
    }

    private fun handleError(exception: Exception) {
        Log.e("Ktor", "Ошибка сети: ${exception.message}")
        // Показать сообщение об ошибке пользователю
    }
}