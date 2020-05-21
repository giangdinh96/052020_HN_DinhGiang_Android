package vn.teko.test.extension

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

fun SwipeRefreshLayout.initColorDefault() {
    setColorSchemeResources(
        android.R.color.holo_red_light,
        android.R.color.holo_blue_light,
        android.R.color.holo_green_light,
        android.R.color.holo_orange_light
    )
}