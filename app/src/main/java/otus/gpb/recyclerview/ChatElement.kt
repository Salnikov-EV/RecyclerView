package otus.gpb.recyclerview

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@kotlinx.serialization.InternalSerializationApi
@Serializable
sealed class ChatItem {

    @Serializable
    data class UserItem(
        val userName: String,
        val isMuted: Boolean,
        val isVerified: Boolean,
        val message: String,
        val messageTime: LocalDateTime,
        val userAvatarUrl: String,
        var isUnread: Boolean,
        var unreadCount: Int,
        val isSend: Boolean,
        val isDelivered: Boolean,
        var isArchived: Boolean,
        val isScam: Boolean
    ) : ChatItem()

    @Serializable
    data class GroupItem(
        val chatName: String,
        val userName: String,
        val isMuted: Boolean,
        val isVerified: Boolean,
        val message: String,
        val messageTime: LocalDateTime,
        val userAvatarUrl: String,
        var isUnread: Boolean,
        var unreadCount: Int,
        val isSend: Boolean,
        val isDelivered: Boolean,
        var isArchived: Boolean,
        val isScam: Boolean
    ) : ChatItem()
}