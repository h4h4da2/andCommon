package com.hhda.andcommon.widget.recyclerview

import android.content.Context
import com.hhda.andcommon.widget.recyclerview.page.IPage
import com.hhda.andcommon.widget.recyclerview.page.LoadState
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

fun SmartRefreshLayout.setDefaultRefreshHeaderAndFooter(context: Context) {
    setRefreshHeader(ClassicsHeader(context))
    setRefreshFooter(ClassicsFooter(context))
}



fun SmartRefreshLayout.handle(page: IPage) {

    val currentLoadState = page.getLoadState()

    //加载第一页数据，显示刷新动画。
    if (page.isFirstPage()) {
        if (currentLoadState == LoadState.LOADING) {
            if (!isRefreshing) {
                autoRefresh(0, 0, 0f, true)
            }
        } else if (currentLoadState == LoadState.FAIL || currentLoadState == LoadState.SUCCEED) {
            finishRefresh(400)
        }
    } else {
        if (currentLoadState == LoadState.SUCCEED) {
            finishLoadMore(true)
        } else if (currentLoadState == LoadState.FAIL) {
            finishLoadMore(false)
        }
    }

    //判断是否能继续拉数据
    var canLoadMore = true
    if (page.isFirstPage() && (currentLoadState == LoadState.UNINITIATED || currentLoadState == LoadState.LOADING)) {
        canLoadMore = false
    }
    setEnableLoadMore(canLoadMore)
    setNoMoreData(page.hasNext())

}

