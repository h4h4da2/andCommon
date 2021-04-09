package com.hhda.andcommon.widget.recyclerview.page

interface IPageHandler {

    fun onLoadStart()

    fun onLoadComplete(data: List<Any>?, error: Any?)

}