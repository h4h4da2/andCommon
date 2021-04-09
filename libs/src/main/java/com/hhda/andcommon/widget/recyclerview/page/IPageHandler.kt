package com.hhda.andcommon.widget.recyclerview.page

interface IPageHandler {

    fun onLoadBegin()

    fun onLoadComplete(data: List<Any>?, error: Any?)

}