package com.hhda.demo.dto

data class BaseResult<T>(
    val data: T?,
    val errorCode: Int,
    val errorMsg: String
)