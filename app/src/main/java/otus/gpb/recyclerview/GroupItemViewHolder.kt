package otus.gpb.recyclerview

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class GroupItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val elementView: ConstraintLayout = itemView.findViewById(R.id.chat_item_group_layout)

}