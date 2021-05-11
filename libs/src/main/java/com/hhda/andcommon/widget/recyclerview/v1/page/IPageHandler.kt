package com.hhda.andcommon.widget.recyclerview.v1.page

interface IPageHandler {

    fun onLoadStart(isRefresh: Boolean)

    fun onLoadComplete(result: Any?, error: Any?)

    fun onDataChange(reduce: androidx.arch.core.util.Function<List<Any>, List<Any>>)


}