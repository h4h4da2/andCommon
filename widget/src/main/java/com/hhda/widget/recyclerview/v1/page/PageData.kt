package com.hhda.widget.recyclerview.v1.page

class PageData(
    val isFirstPage: Boolean,
    val pageList: List<Any>?,
    val hasMore: Boolean,
    val error: Any?,
    // 根据业务场景存放一些数据
    val obj: Any? = null
) {
}