package vn.teko.test.extension

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setup(
    spanCount: Int = 3,
    orientation: Int = RecyclerView.VERTICAL,
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.context)
) {
    this.layoutManager = layoutManager
    when (layoutManager) {
        is LinearLayoutManager -> layoutManager.orientation = orientation
        is GridLayoutManager -> {
            layoutManager.spanCount = spanCount
            layoutManager.orientation = orientation
        }
    }
    this.clipToPadding = false
    this.setHasFixedSize(true)
}