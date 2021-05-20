package com.hhda.andcommon.widget.recyclerview.v1.page

interface IPageManager {

    /**
     * 刷新，重置page
     */
    fun resetPage()

    /**
     * 加载下一页
     */
    fun loadNextPage()

    fun onLoadComplete(pageData:PageData)

}