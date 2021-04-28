package com.hhda.andcommon.widget.recyclerview.page.impl

import com.hhda.andcommon.widget.recyclerview.RefreshLoadMoreView
import com.hhda.andcommon.widget.recyclerview.page.IPageManager


import com.hhda.andcommon.widget.recyclerview.page.IPageHandler
import com.hhda.andcommon.widget.recyclerview.page.IPageLoader

/**
 * 常规业务下的分页处理
 */
class CommonPageHandler(
    private val refreshLoadMoreView: RefreshLoadMoreView,
    private val pageLoader: IPageLoader,
    private val pageManager: IPageManager
) : IPageHandler {

    var mData: List<Any> = emptyList()

    override fun onLoadStart(isRefresh: Boolean) {

        val isLoading = refreshLoadMoreView.binding.refreshLayout.isRefreshing

        if (isRefresh) {
            //重置 nextPage
            pageManager.onRefresh()
        }
        if (isRefresh && !isLoading) {
            refreshLoadMoreView.binding.refreshLayout.autoRefreshAnimationOnly()
        }
        doLoadData()
    }

    override fun onLoadComplete(result: Any?, error: Any?) {

        val pageData = pageManager.handleResult(result, error)

        //加载失败情况
        if (error != null) {
            if (pageData.isFirstPage) {
                refreshLoadMoreView.showErr(error)
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

            refreshLoadMoreView.setData(mData)
            if (pageData.isFirstPage) {

                refreshLoadMoreView.binding.refreshLayout.finishRefresh(true)
                if (pageData.pageList.isNullOrEmpty()) {
                    refreshLoadMoreView.showEmpty()
                    refreshLoadMoreView.binding.recyclerView.scrollToPosition(0)
                } else {
                    refreshLoadMoreView.showContent()
                }
            } else {
                refreshLoadMoreView.showContent()

                if (pageData.hasMore) {
                    refreshLoadMoreView.binding.refreshLayout.finishLoadMore(true)
                } else {
                    refreshLoadMoreView.binding.refreshLayout.finishLoadMoreWithNoMoreData()
                }
            }

        }

    }


    private fun doLoadData() {
        val nextPage = pageManager.getNextPage()
        nextPage ?: return
        pageLoader.doLoad(nextPage)
    }

}