package com.hhda.andcommon.widget.recyclerview.page.impl

import com.hhda.andcommon.widget.recyclerview.page.IPage
import com.hhda.andcommon.widget.recyclerview.page.LoadState


data class CommonPage(
    private val data: List<Any>? = null,
    private val pageIndex: Int = DEF_START_INDEX,
    private val loadState: LoadState = LoadState.UNINITIATED
) : IPage {

    companion object {
        const val DEF_START_INDEX = 1
        const val DEF_PAGE_SIZE = 20
    }

    override fun hasNext(): Boolean {
        if (isFirstPage()) {
            if (loadState == LoadState.LOADING || loadState == LoadState.UNINITIATED) {
                return true
            }

        }
        return !data.isNullOrEmpty() && data.size >= DEF_PAGE_SIZE
    }


    override fun getCurrentPageKey(): Any? {
        return pageIndex
    }

    override fun getLoadState(): LoadState {
        return loadState
    }

    override fun isFirstPage(): Boolean {
        return pageIndex == DEF_START_INDEX
    }

}




