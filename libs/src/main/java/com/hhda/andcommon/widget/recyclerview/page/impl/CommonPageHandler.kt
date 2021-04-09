package com.hhda.andcommon.widget.recyclerview.page.impl

import com.hhda.andcommon.widget.recyclerview.RefreshLoadMoreView


import com.hhda.andcommon.widget.recyclerview.page.IPageHandler
import com.hhda.andcommon.widget.recyclerview.page.IPageLoader

/**
 * 常规业务下的分页处理
 */
class CommonPageHandler(
    private val refreshLoadMoreView: RefreshLoadMoreView,
    private val pageLoader: IPageLoader
) : IPageHandler {

    private var mPage: CommonPage = CommonPage()

    var mData: List<Any> = emptyList()

    override fun onLoadStart() {
        val isLoading = refreshLoadMoreView.binding.refreshLayout.isRefreshing
        if (mPage.isLoadFirstPage() && !isLoading) {
            refreshLoadMoreView.binding.refreshLayout.autoRefresh(0, 0, 0f, true)
        }
        doLoadData()
    }


    override fun onLoadComplete(data: List<Any>?, error: Any?) {
        //加载失败情况
        if (error != null) {
            if (mPage.isLoadFirstPage()) {
                refreshLoadMoreView.showErr(error)
            } else {
                refreshLoadMoreView.binding.refreshLayout.finishLoadMore(false)
            }

        } else {
            //加载成功的情况

            if (mPage.isLoadFirstPage()) {
                mData = emptyList()
            }
            if (!data.isNullOrEmpty()) {
                val tmpList = mData.toMutableList()
                tmpList.addAll(data)
                mData = tmpList
            }

            refreshLoadMoreView.setData(mData)
            if (mPage.isLoadFirstPage()) {

                refreshLoadMoreView.binding.refreshLayout.finishRefresh(400)
                if (data.isNullOrEmpty()) {
                    refreshLoadMoreView.showEmpty()
                } else {
                    refreshLoadMoreView.showContent()
                }
            } else {
                refreshLoadMoreView.showContent()
                val hasMore = !data.isNullOrEmpty() && data.size >= CommonPage.DEF_PAGE_SIZE
                if (hasMore) {
                    refreshLoadMoreView.binding.refreshLayout.finishLoadMore(true)
                } else {
                    refreshLoadMoreView.binding.refreshLayout.finishLoadMoreWithNoMoreData()
                }
            }

            val curPage = mPage.copy(pageIndex = mPage.pageIndex + 1)
            mPage = curPage

        }

    }

    private fun doLoadData() {
        val nextPage = mPage.copy(pageIndex = mPage.pageIndex + 1)
        pageLoader.doLoad(nextPage)
    }

}