package vn.teko.test.extension

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import vn.teko.test.common.RefreshStatus

fun SwipeRefreshLayout.initColorDefault() {
    setColorSchemeResources(
        android.R.color.holo_red_light,
        android.R.color.holo_blue_light,
        android.R.color.holo_green_light,
        android.R.color.holo_orange_light
    )
}

fun SwipeRefreshLayout.updateRefresh(refreshStatus: RefreshStatus) {
    when (refreshStatus) {
        RefreshStatus.ENABLE -> isEnabled = true
        RefreshStatus.DISABLE -> isEnabled = false
        RefreshStatus.SHOW -> isRefreshing = true
        RefreshStatus.HIDE -> isRefreshing = false
    }
}