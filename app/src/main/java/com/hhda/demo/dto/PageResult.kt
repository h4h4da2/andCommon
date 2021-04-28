package com.hhda.demo.dto

class PageResult<T>(
    var datas: List<T>?,
    var curPage: Int,
    var over: Boolean
)