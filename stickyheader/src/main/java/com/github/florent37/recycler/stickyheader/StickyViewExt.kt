package com.github.florent37.recycler.stickyheader

import android.view.View
import android.view.ViewGroup

/**
 * Properly measures and layouts the top sticky header.
 * @returns the calculated height
 * @param parent ViewGroup: RecyclerView in this case.
 */
fun View.fixLayoutHeight(parent: ViewGroup) : Int {
    val view = this

    // Specs for parent (RecyclerView)
    val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
    val heightSpec =
        View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)
    // Specs for children (headers)
    val childWidthSpec = ViewGroup.getChildMeasureSpec(
        widthSpec,
        parent.paddingLeft + parent.paddingRight,
        view.layoutParams.width
    )
    val childHeightSpec = ViewGroup.getChildMeasureSpec(
        heightSpec,
        parent.paddingTop + parent.paddingBottom,
        view.layoutParams.height
    )
    view.measure(childWidthSpec, childHeightSpec)

    val height = view.measuredHeight

    view.layout(0, 0, view.measuredWidth, height)

    return height
}