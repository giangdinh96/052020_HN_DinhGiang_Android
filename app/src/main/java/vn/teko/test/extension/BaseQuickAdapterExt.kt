package vn.teko.test.extension

import com.chad.library.adapter.base.BaseQuickAdapter
import vn.teko.test.common.LoadMoreStatus

fun BaseQuickAdapter<*, *>.updateLoadMore(loadMoreStatus: LoadMoreStatus) {
    when (loadMoreStatus) {
        LoadMoreStatus.ENABLE -> loadMoreModule.isEnableLoadMore = true
        LoadMoreStatus.DISABLE -> loadMoreModule.isEnableLoadMore = false
        LoadMoreStatus.COMPLETE -> loadMoreModule.loadMoreComplete()
        LoadMoreStatus.END -> loadMoreModule.loadMoreEnd()
        LoadMoreStatus.ERROR -> loadMoreModule.loadMoreFail()
    }
}