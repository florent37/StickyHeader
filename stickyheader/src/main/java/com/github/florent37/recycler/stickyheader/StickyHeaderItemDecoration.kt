package com.github.florent37.recycler.stickyheader

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class StickyHeaderItemDecoration<VH : RecyclerView.ViewHolder> : RecyclerView.ItemDecoration {

    private var adapter: StickyAdapter<VH>? = null

    constructor(adapter: StickyHeader.Adapter<VH>){
        this.adapter = StickyAdapter.FullAdapter(adapter)
    }

    constructor(
        recyclerAdapter: RecyclerView.Adapter<VH>,
        adapter: StickyHeader.AbstractAdapter
    ) {
        this.adapter = StickyAdapter.RecyclerAdapterAndAbstractAdapter<VH>(
            adapter= adapter,
            recyclerAdapter = recyclerAdapter
        )
    }

    private var stickyHeaderHeight = 0
    private var currentTopChildPosition: Int? = null
    private var currentTopChildHeaderPosition: Int? = null
    private var currentHeaderViewHolder: RecyclerView.ViewHolder? = null

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)

        val adapter = this.adapter ?: return

        val topChild = parent.getChildAt(0) ?: return
        val topChildPosition = parent.getChildAdapterPosition(topChild)
        if (topChildPosition == RecyclerView.NO_POSITION) {
            return
        }

        val headerPos = if (currentTopChildPosition == topChildPosition) {
            currentTopChildHeaderPosition
        } else {
            currentHeaderViewHolder = null
            currentTopChildHeaderPosition = getHeaderPositionForItem(adapter, topChildPosition)
            currentTopChildHeaderPosition
        }
        currentTopChildPosition = topChildPosition

        if (headerPos != null) {
            val currentHeader = if (currentHeaderViewHolder != null) {
                currentHeaderViewHolder!!.itemView
            } else {
                createHeaderViewForPosition(adapter, headerPos, parent)
            }
            val contactPoint = currentHeader.bottom
            val childInContact = getChildInContact(parent, adapter, contactPoint, headerPos)
            if (childInContact != null && adapter.isStickyHeader(
                    parent.getChildAdapterPosition(
                        childInContact
                    )
                )
            ) {
                moveHeader(canvas, currentHeader, childInContact)
                return
            }
            drawHeader(canvas, currentHeader)
        }
    }

    private fun getHeaderPositionForItem(adapter: StickyAdapter<VH>, itemPosition: Int): Int? {
        for (i in itemPosition downTo 0) {
            if (adapter.isStickyHeader(i)) {
                return i
            }
        }
        return null
    }

    private fun createHeaderViewForPosition(adapter: StickyAdapter<VH>, headerPosition: Int, parent: RecyclerView): View {
        val headerType = adapter.getItemViewType(headerPosition)
        val viewHolder = adapter.onCreateViewHolder(parent, headerType)
        val header = viewHolder.itemView
        adapter.onBindViewHolder(viewHolder, headerPosition)
        this.stickyHeaderHeight = header.fixLayoutHeight(parent)
        this.currentHeaderViewHolder = viewHolder
        return header
    }

    private fun drawHeader(canvas: Canvas, headerView: View) {
        canvas.save()
        canvas.translate(0f, 0f)
        headerView.draw(canvas)
        canvas.restore()
    }

    private fun moveHeader(canvas: Canvas, currentHeaderView: View, nextHeaderView: View) {
        canvas.save()
        canvas.translate(0f, nextHeaderView.top - currentHeaderView.height.toFloat())
        currentHeaderView.draw(canvas)
        canvas.restore()
    }

    private fun getChildInContact(
        parent: RecyclerView,
        adapter: StickyAdapter<VH>,
        contactPoint: Int,
        currentHeaderPos: Int
    ): View? {
        var childInContact: View? = null
        for (i in 0 until parent.childCount) {
            var heightTolerance = 0
            val child = parent.getChildAt(i)
            //measure height tolerance with child if child is another header
            if (currentHeaderPos != i) {
                val isChildHeader =
                    adapter.isStickyHeader(parent.getChildAdapterPosition(child))
                if (isChildHeader) {
                    heightTolerance = stickyHeaderHeight - child.height
                }
            }
            //add heightTolerance if child top be in display area
            var childBottomPosition: Int
            childBottomPosition = if (child.top > 0) {
                child.bottom + heightTolerance
            } else {
                child.bottom
            }
            if (childBottomPosition > contactPoint) {
                if (child.top <= contactPoint) {
                    // This child overlaps the contactPoint
                    childInContact = child
                    break
                }
            }
        }
        return childInContact
    }

}