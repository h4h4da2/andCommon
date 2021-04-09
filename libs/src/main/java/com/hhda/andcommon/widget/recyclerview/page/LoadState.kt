package com.hhda.andcommon.widget.recyclerview.page

enum class LoadState {
    //未加载
    UNINITIATED,

    //发出请求，开始加载
    LOADING,

    //加载失败
    FAIL,

    //加载成功
    SUCCEED;
}