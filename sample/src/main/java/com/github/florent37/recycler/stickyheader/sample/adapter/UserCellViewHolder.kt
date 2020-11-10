package com.github.florent37.recycler.stickyheader.sample.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.recycler.stickyheader.sample.R
import com.github.florent37.recycler.stickyheader.sample.model.User

class UserCellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name : TextView = itemView.findViewById(R.id.name)

    fun bind(user: User) {
        name.text = user.name
    }

}