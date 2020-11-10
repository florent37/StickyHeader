package com.github.florent37.recycler.stickyheader

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StickyHeader {

    companion object {
        fun setupWith(recyclerView: RecyclerView, adapter: StickyHeader.Adapter<*>) : StickyHeader {
            return StickyHeader().apply {
                withAdapter(adapter)
                attachTo(recyclerView)
            }
        }
        fun setupWith(recyclerView: RecyclerView, adapter: StickyRecyclerAdapter<*>) : StickyHeader {
            return StickyHeader().apply {
                withAdapter(adapter)
                attachTo(recyclerView)
            }
        }
    }

    private var itemDecoration : StickyHeaderItemDecoration<*>? = null

    fun <VH: RecyclerView.ViewHolder> withAdapter(adapter: StickyHeader.Adapter<VH>) : StickyHeader {
        itemDecoration = StickyHeaderItemDecoration(adapter)
        return this
    }

    fun <VH: RecyclerView.ViewHolder> withAdapter(adapter: StickyRecyclerAdapter<VH>) : StickyHeader {
        itemDecoration = StickyHeaderItemDecoration(recyclerAdapter= adapter, adapter= adapter)
        return this
    }

    fun <VH: RecyclerView.ViewHolder> withAbstractAdapter(recyclerAdapter: RecyclerView.Adapter<VH>, adapter: AbstractAdapter) : StickyHeader {
        itemDecoration = StickyHeaderItemDecoration(recyclerAdapter= recyclerAdapter, adapter= adapter)
        return this
    }

    fun attachTo(recyclerView: RecyclerView) {
        itemDecoration?.let {
            recyclerView.addItemDecoration(it)
        }
    }

    fun detachFrom(recyclerView: RecyclerView) {
        itemDecoration?.let {
            recyclerView.removeItemDecoration(it)
        }
    }

    interface Adapter<VH: RecyclerView.ViewHolder> : AbstractAdapter {
        /**
         * It's directly available in an adapter
         * This method gets called by [StickyHeader] to get layout ViewHolder and View
         */
        fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): VH

        /**
         * This method gets called by [StickyHeader] to setup the header ViewHolder.
         * It's directly available in an adapter
         */
        fun onBindViewHolder(viewHolder: VH, position: Int)

        fun getItemViewType(position: Int): Int
    }

    interface AbstractAdapter {

        /**
         * This method gets called by [StickyHeader] to verify whether the item represents a header.
         * @param itemPosition int.
         * @return true, if item at the specified adapter's position represents a header.
         */
        fun isStickyHeader(itemPosition: Int): Boolean

    }
}

internal sealed class StickyAdapter<VH: RecyclerView.ViewHolder> {

    fun isStickyHeader(childAdapterPosition: Int): Boolean {
        return when(this){
            is FullAdapter<VH> -> this.adapter.isStickyHeader(childAdapterPosition)
            is RecyclerAdapterAndAbstractAdapter<VH> -> this.adapter.isStickyHeader(childAdapterPosition)
        }
    }

    fun getItemViewType(position: Int): Int {
        return when(this){
            is FullAdapter -> this.adapter.getItemViewType(position)
            is RecyclerAdapterAndAbstractAdapter -> this.recyclerAdapter.getItemViewType(position)
        }
    }

    fun onCreateViewHolder(parent: RecyclerView, headerType: Int): VH {
        return when(this){
            is FullAdapter -> this.adapter.onCreateViewHolder(parent, headerType)
            is RecyclerAdapterAndAbstractAdapter -> this.recyclerAdapter.onCreateViewHolder(parent, headerType)
        }
    }

    fun onBindViewHolder(viewHolder: VH, headerPosition: Int) {
        when(this){
            is FullAdapter -> this.adapter.onBindViewHolder(viewHolder, headerPosition)
            is RecyclerAdapterAndAbstractAdapter -> this.recyclerAdapter.onBindViewHolder(viewHolder, headerPosition)
        }
    }

    class FullAdapter<VH : RecyclerView.ViewHolder>(
        val adapter: StickyHeader.Adapter<VH>
    ) : StickyAdapter<VH>()
    class RecyclerAdapterAndAbstractAdapter<VH : RecyclerView.ViewHolder>(
        val adapter: StickyHeader.AbstractAdapter,
        val recyclerAdapter: RecyclerView.Adapter<VH>
    ) : StickyAdapter<VH>()
}
