package otus.gpb.recyclerview

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @OptIn(InternalSerializationApi::class)
    @Test
    fun serializeChatElement() {
        val chatElements: List<ChatItem> = listOf(
            ChatItem.GroupItem(
                chatName = "Pizza",
                userName = "jija",
                isMuted = true,
                isVerified = false,
                message = "Yes, they are necessary",
                messageTime = LocalDateTime(
                    year = 2026,
                    month = 6,
                    day = 16,
                    hour = 11,
                    minute = 38,
                    second = 0,
                    nanosecond = 0
                ),
                userAvatarUrl = "https://avatar/1.jpg",
                isUnread = false,
                unreadCount = 0,
                isSend = false,
                isDelivered = false,
                isArchived = false,
                isScam = false
            ),
            ChatItem.UserItem(
                userName = "Elon",
                isMuted = true,
                isVerified = false,
                message = "I love /r/Reddit",
                messageTime = LocalDateTime(
                    year = 2026,
                    month = 6,
                    day = 16,
                    hour = 12,
                    minute = 44,
                    second = 0,
                    nanosecond = 0
                ),
                userAvatarUrl = "https://avatar/2.jpg",
                isUnread = false,
                unreadCount = 0,
                isSend = false,
                isDelivered = false,
                isArchived = false,
                isScam = false
            ),
            ChatItem.UserItem(
                userName = "Pasha",
                isMuted = true,
                isVerified = true,
                message = "How are you?",
                messageTime = LocalDateTime(
                    year = 2026,
                    month = 6,
                    day = 12,
                    hour = 10,
                    minute = 11,
                    second = 0,
                    nanosecond = 0
                ),
                userAvatarUrl = "https://avatar/3.jpg",
                isUnread = false,
                unreadCount = 0,
                isSend = false,
                isDelivered = false,
                isArchived = false,
                isScam = false
            ),
            ChatItem.GroupItem(
                chatName = "Telegram Support",
                userName = "Support",
                isMuted = false,
                isVerified = true,
                message = "Yes it happened",
                messageTime = LocalDateTime(
                    year = 2026,
                    month = 6,
                    day = 16,
                    hour = 11,
                    minute = 38,
                    second = 0,
                    nanosecond = 0
                ),
                userAvatarUrl = "https://avatar/1.jpg",
                isUnread = false,
                unreadCount = 1,
                isSend = false,
                isDelivered = false,
                isArchived = false,
                isScam = false
            ),
            ChatItem.UserItem(
                userName = "Karina",
                isMuted = false,
                isVerified = false,
                message = "Okay",
                messageTime = LocalDateTime(
                    year = 2026,
                    month = 6,
                    day = 12,
                    hour = 10,
                    minute = 11,
                    second = 0,
                    nanosecond = 0
                ),
                userAvatarUrl = "https://avatar/karina.jpg",
                isUnread = false,
                unreadCount = 0,
                isSend = true,
                isDelivered = false,
                isArchived = false,
                isScam = false
            ),
            ChatItem.UserItem(
                userName = "Marilyn",
                isMuted = false,
                isVerified = false,
                message = "Will it happen",
                messageTime = LocalDateTime(
                    year = 2026,
                    month = 6,
                    day = 12,
                    hour = 10,
                    minute = 11,
                    second = 0,
                    nanosecond = 0
                ),
                userAvatarUrl = "https://avatar/marilyn.jpg",
                isUnread = false,
                unreadCount = 0,
                isSend = true,
                isDelivered = true,
                isArchived = false,
                isScam = true
            ),
        )

        val json = Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }

        val jsonString = json.encodeToString(chatElements)
        println(jsonString)

        val fromJson: List<ChatItem> = json.decodeFromString(jsonString)
        assertEquals(fromJson[0], chatElements[0])
        assertEquals(fromJson[1], chatElements[1])
        assertEquals((fromJson[1] as ChatItem.UserItem).userName, (chatElements[1] as ChatItem.UserItem).userName)
    }
}