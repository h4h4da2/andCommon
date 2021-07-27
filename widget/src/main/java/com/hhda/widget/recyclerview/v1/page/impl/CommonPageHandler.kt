package com.hhda.widget.recyclerview.v1.page.impl


import com.hhda.widget.recyclerview.v1.RefreshLoadMoreView
import com.hhda.widget.recyclerview.v1.page.IPageHandler
import com.hhda.widget.recyclerview.v1.page.IPageManager
import com.hhda.widget.recyclerview.v1.page.PageData

/**
 * 常规业务下的分页处理
 */
class CommonPageHandler(
    private val refreshLoadMoreView: RefreshLoadMoreView,
    private val pageManager: IPageManager
) : IPageHandler {

    private var mData: List<Any> = emptyList()

    override fun onLoadStart(isRefresh: Boolean) {

        if (isRefresh) {
            //重置 nextPage
            pageManager.resetPage()
        }
        if (isRefresh && !refreshLoadMoreView.binding.refreshLayout.isRefreshing) {
            refreshLoadMoreView.binding.refreshLayout.autoRefreshAnimationOnly()
        }
        pageManager.loadNextPage()
    }

    override fun onLoadComplete(pageData: PageData) {

        pageManager.onLoadComplete(pageData)

        //加载失败情况
        if (pageData.error != null) {
            if (pageData.isFirstPage) {
                refreshLoadMoreView.showErr(pageData.error)
                refreshLoadMoreView.binding.refreshLayout.finishRefresh(false)
            } else {
                refreshLoadMoreView.binding.refreshLayout.finishLoadMore(false)
            }

        } else {
            //加载成功的情况

            if (pageData.isFirstPage) {
                mData = emptyList()
            }
            if (pageData.pageList != null) {
                val tmpList = mData.toMutableList()
                tmpList.addAll(pageData.pageList)
                mData = tmpList
            }


            if (pageData.isFirstPage) {

                refreshLoadMoreView.setData(mData, true)
                refreshLoadMoreView.binding.refreshLayout.finishRefresh(
                    300,
                    true,
                    !pageData.hasMore
                )
                if (pageData.pageList.isNullOrEmpty()) {
                    refreshLoadMoreView.showEmpty()
                } else {
                    refreshLoadMoreView.showContent()
                }

            } else {
                refreshLoadMoreView.setData(mData, false)
                refreshLoadMoreView.showContent()

                if (pageData.hasMore) {
                    refreshLoadMoreView.binding.refreshLayout.finishLoadMore(true)
                } else {
                    refreshLoadMoreView.binding.refreshLayout.finishLoadMoreWithNoMoreData()
                }
            }
        }
    }


    override fun onDataChange(reduce: androidx.arch.core.util.Function<List<Any>, List<Any>>) {
        mData = reduce.apply(mData)
        refreshLoadMoreView.setData(mData, false)
    }

}