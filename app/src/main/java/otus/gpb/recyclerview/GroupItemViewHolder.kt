package otus.gpb.recyclerview

import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
class GroupItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val elementView: ConstraintLayout = itemView.findViewById(R.id.chat_item_group_layout)

    fun bind(item: ChatItem.GroupItem) {
        elementView.findViewById<TextView>(R.id.group_name).text = item.chatName
        elementView.findViewById<TextView>(R.id.message).text = item.message

        if (item.unreadCount > 0) {
            val messagePlusUnreadCount = "${item.message} (${item.unreadCount})"
            elementView.findViewById<TextView>(R.id.message).text = messagePlusUnreadCount
        } else {
            elementView.findViewById<TextView>(R.id.message).text = item.message
        }
    }

}