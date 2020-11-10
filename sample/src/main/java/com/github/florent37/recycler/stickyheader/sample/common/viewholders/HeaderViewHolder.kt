package com.github.florent37.recycler.stickyheader.sample.common.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.recycler.stickyheader.sample.R
import com.github.florent37.recycler.stickyheader.sample.common.model.UserHeader

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title : TextView = itemView.findViewById(R.id.label)

    fun bind(userHeader: UserHeader) {
        title.text = userHeader.title
    }

}