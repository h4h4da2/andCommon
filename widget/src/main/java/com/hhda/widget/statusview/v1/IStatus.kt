package com.hhda.widget.statusview.v1

interface IStatus {

    //显示业务布局
    object SHOW_CONTENT

    //错误，异常布局
    object ERROR : IStatus


    object FAILED : IStatus

    object SUCCEED : IStatus

    //无数据的布局
    object EMPTY : IStatus

    //Loading 布局
    object LOADING : IStatus


}