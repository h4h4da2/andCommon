package com.hhda.demo.wanandroid.page

import com.hhda.andcommon.widget.recyclerview.v1.page.IPageManager
import com.hhda.andcommon.widget.recyclerview.v1.page.PageData
import com.hhda.demo.dto.PageResult

class WanAndroidPageManager(private val loadFunc: (Any) -> Unit) : IPageManager {

    companion object {
        const val FIRST_INDEX = 0
    }

    var mPage = WanAndroidPage(FIRST_INDEX - 1)

    private fun getNextPage(): Any {
        return mPage.copy(curPage = mPage.curPage + 1)
    }

    override fun handleResult(data: Any?, err: Any?): PageData {
        val isFirstPage = mPage.curPage == FIRST_INDEX - 1
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

    override fun resetPage() {
        mPage = WanAndroidPage(FIRST_INDEX - 1)
    }

    override fun loadNextPage() {
        loadFunc(getNextPage())
    }
}