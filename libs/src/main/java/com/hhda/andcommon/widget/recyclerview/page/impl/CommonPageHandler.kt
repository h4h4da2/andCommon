package com.hhda.andcommon.widget.recyclerview.page.impl

import com.hhda.andcommon.widget.recyclerview.RefreshLoadMoreView
import com.hhda.andcommon.widget.recyclerview.page.IPage

import com.hhda.andcommon.widget.recyclerview.page.IPageHandler
import com.hhda.andcommon.widget.recyclerview.page.LoadState

class CommonPageHandler(private val refreshLoadMoreView: RefreshLoadMoreView) : IPageHandler {

    private var mPage: CommonPage = CommonPage()

    private var mData: List<Any> = emptyList()


//    override fun onReqBegin() {

//
//    }

    override fun onLoadBegin() {
        mPage = mPage.copy(loadState = LoadState.LOADING)
        if (mPage.isFirstPage()) {
            refreshLoadMoreView.binding.refreshLayout.autoRefresh(0, 0, 0f, true)
        }

        doLoadData()
    }


    override fun onLoadComplete(data: List<Any>?, error: Any?) {
        //数据加载成功
        if (data != null) {
            if (mPage.isFirstPage()) {
                refreshLoadMoreView.binding.refreshLayout.finishLoadMore(400)
            }
        } else {
            //加载失败
            mPage = mPage.copy(loadState = LoadState.FAIL)

        }
    }

    private fun doLoadData() {

    }

}