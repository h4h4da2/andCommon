package com.hhda.andcommon.widget.recyclerview.page.impl

import com.hhda.andcommon.widget.recyclerview.page.IPage

/**
 * 常见的分页
 */
data class CommonPage(
    val pageIndex: Int = DEF_START_INDEX - 1
) : IPage {

    companion object {
        const val DEF_START_INDEX = 1
        const val DEF_PAGE_SIZE = 20
    }


    override fun getCurrentPageKey(): Any? {
        return pageIndex
    }

    override fun nextPage(): IPage {
        return this.copy(pageIndex = pageIndex + 1)
    }

    override fun isFirstPage(): Boolean {
        return pageIndex == DEF_START_INDEX - 1
    }

    fun isLoadFirstPage(): Boolean {
        return pageIndex == DEF_START_INDEX - 1
    }

}




