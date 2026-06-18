package otus.gpb.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
class ChatViewAdapter @OptIn(InternalSerializationApi::class) constructor(
    private val data: MutableList<ChatItem>,
    private val onItemClick: (ChatItem, Int) -> Unit,
    private val onLongItemClick: (ChatItem, Int) -> Unit,
    private val onSwipeLeft: (ChatItem, Int) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is ChatItem.UserItem -> VIEW_TYPE_USER
            is ChatItem.GroupItem -> VIEW_TYPE_GROUP
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_USER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_item_user, parent, false)
                UserItemViewHolder(view)
            }

            VIEW_TYPE_GROUP -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_item_group, parent, false)
                GroupItemViewHolder(view)
            }

            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is UserItemViewHolder -> {
                holder.bind(item = data[position] as ChatItem.UserItem)
            }
            is GroupItemViewHolder -> {
                holder.bind(item = data[position] as ChatItem.GroupItem)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    companion object {
        private const val VIEW_TYPE_USER = 0
        private const val VIEW_TYPE_GROUP = 1
    }

    fun updateChatList(chats: List<ChatItem>) {
        data.clear()
        data.addAll(chats)
        notifyDataSetChanged()
    }

}