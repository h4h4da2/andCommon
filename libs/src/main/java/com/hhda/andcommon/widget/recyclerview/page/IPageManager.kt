package com.hhda.andcommon.widget.recyclerview.page

interface IPageManager {

    /**
     * 获取下一分页
     */
    fun getNextPage(): Any?

    fun handleResult(data: Any?, err: Any?): PageData

    fun onRefresh()

}