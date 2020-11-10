package com.github.florent37.recycler.stickyheader

import androidx.recyclerview.widget.RecyclerView

abstract class StickyRecyclerAdapter<VM : RecyclerView.ViewHolder> : RecyclerView.Adapter<VM>(), StickyHeader.AbstractAdapter {

    private val stickyHeader = StickyHeader()

    init {
        stickyHeader.withAbstractAdapter(recyclerAdapter = this, adapter = this)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        stickyHeader.attachTo(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        stickyHeader.detachFrom(recyclerView)
    }

}


