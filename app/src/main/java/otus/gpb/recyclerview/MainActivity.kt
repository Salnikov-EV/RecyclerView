package otus.gpb.recyclerview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
class MainActivity : AppCompatActivity() {

    private lateinit var chatApiService: ChatApiService
    private lateinit var adapter: ChatViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chatApiService = ChatApiService()
        fetchChats()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ChatViewAdapter(
            data = mutableListOf(),
            onItemClick = { item, position ->
                when (item) {
                    is ChatItem.UserItem -> item.isUnread = false
                    is ChatItem.GroupItem -> item.isUnread = false
                }
            },
            onLongItemClick = { item, position -> },
            onSwipeLeft = { item, position ->
                when (item) {
                    is ChatItem.UserItem -> item.isArchived = true
                    is ChatItem.GroupItem -> item.isArchived = true
                }
            },
        )
        recyclerView.adapter = adapter


//        private val onItemClick: (ChatItem, Int) -> Unit,
//        private val onLongItemClick: (ChatItem, Int) -> Unit,
//        private val onSwipeLeft: (ChatItem, Int) -> Unit,

//        val userAvatar = findViewById<ImageView>(R.id.user_avatar)
//        val imageUrl = "https://storage.yandexcloud.net/recycle-view/user_avatar_1.jpeg"
//
//        Glide.with(this)
//            .load(imageUrl)
//            .placeholder(R.drawable.ic_avatar_placeholder)
//            .error(R.drawable.ic_avatar_error)
//            .circleCrop()
//            .into(userAvatar)

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
        chats.forEach { chat ->
            when(chat) {
                is ChatItem.UserItem -> Log.d("Ktor", "chat: ${chat.userName}, ${chat.message}")
                is ChatItem.GroupItem -> Log.d("Ktor", "chat: ${chat.userName}, ${chat.message}")
            }
        }
        adapter.updateChatList(chats)
    }

    private fun handleError(exception: Exception) {
        Log.e("Ktor", "Ошибка сети: ${exception.message}")
    }
}