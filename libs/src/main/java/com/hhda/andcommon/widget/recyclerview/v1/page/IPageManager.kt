package com.hhda.andcommon.widget.recyclerview.v1.page

interface IPageManager {


    /**
     * 处理请求结果
     */
    fun handleResult(data: Any?, err: Any?): PageData

    /**
     * 刷新，重置page
     */
    fun resetPage()

    /**
     * 加载下一页
     */
    fun loadNextPage()

}