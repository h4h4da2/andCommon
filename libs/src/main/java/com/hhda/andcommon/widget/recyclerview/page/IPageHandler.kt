package com.hhda.andcommon.widget.recyclerview.page

interface IPageHandler {

    fun onLoadStart(isRefresh: Boolean)

    fun onLoadComplete(result: Any?, error: Any?)


}