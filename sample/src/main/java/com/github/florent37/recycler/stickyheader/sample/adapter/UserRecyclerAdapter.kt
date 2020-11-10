package com.github.florent37.recycler.stickyheader.sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.florent37.recycler.stickyheader.StickyHeader
import com.github.florent37.recycler.stickyheader.sample.R
import com.github.florent37.recycler.stickyheader.sample.model.User
import com.github.florent37.recycler.stickyheader.sample.model.UserHeader
import com.github.florent37.recycler.stickyheader.sample.model.UserModel

class UserRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeader.Adapter<RecyclerView.ViewHolder> {

    companion object {
        const val TYPE_CELL = 1
        const val TYPE_HEADER = 2
    }

    private val stickyHeader = StickyHeader().withAdapter(this)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        stickyHeader.attachTo(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        stickyHeader.detachFrom(recyclerView)
    }

    var items = listOf<UserModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_CELL -> {
                UserCellViewHolder(layoutInflater.inflate(R.layout.cell_user, parent, false))
            }
            TYPE_HEADER -> {
                HeaderViewHolder(layoutInflater.inflate(R.layout.cell_header, parent, false))
            }
            else -> {
                TODO("cannot happen")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is UserCellViewHolder -> holder.bind(items[position] as User)
            is HeaderViewHolder -> holder.bind(items[position] as UserHeader)
        }
    }

    override fun isStickyHeader(itemPosition: Int): Boolean {
        return getItemViewType(itemPosition) == TYPE_HEADER
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is UserHeader -> TYPE_HEADER
            else -> TYPE_CELL
        }
    }
}


