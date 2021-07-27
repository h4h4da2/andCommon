package com.hhda.widget.recyclerview.v1.page

interface IPageHandler {

    fun onLoadStart(isRefresh: Boolean)

    fun onLoadComplete(pageData: PageData)

    fun onDataChange(reduce: androidx.arch.core.util.Function<List<Any>, List<Any>>)


}