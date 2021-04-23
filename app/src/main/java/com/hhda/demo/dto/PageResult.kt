package com.hhda.demo.dto

data class PageResult<T>(
    val datas: List<T>?,
    val curPage: Int
)