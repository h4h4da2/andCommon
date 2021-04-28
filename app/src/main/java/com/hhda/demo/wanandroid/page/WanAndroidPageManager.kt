package com.hhda.demo.wanandroid.page

import com.hhda.andcommon.widget.recyclerview.page.IPageManager
import com.hhda.andcommon.widget.recyclerview.page.PageData
import com.hhda.demo.dto.PageResult

class WanAndroidPageManager : IPageManager {

    var mPage = WanAndroidPage(0)
    override fun getNextPage(): Any? {
        return mPage.copy(curPage = mPage.curPage + 1)
    }

    override fun handleResult(data: Any?, err: Any?): PageData {
        val isFirstPage = mPage.curPage == -1
        if (err != null) {
            return PageData(isFirstPage = isFirstPage, null, true, err)
        }
        if (data is PageResult<*>) {
            mPage = mPage.copy(curPage = mPage.curPage + 1)
            return PageData(
                isFirstPage = isFirstPage,
                hasMore = !data.over,
                error = err,
                pageList = (data.datas ?: emptyList<Any>()) as List<Any>
            )
        }

        throw Throwable("未知类型")
    }

    override fun onRefresh() {
        mPage = WanAndroidPage(-1)
    }
}