package com.hhda.andcommon.pageinfo

/**
 * 加载行为
 */
data class LoadAction<T>(val step: Int, val data: T?) {


    companion object {
        //未加载
        const val UN_LOAD = 0

        //加载中
        const val LOADING = 1

        //加载成功
        const val SUCCEED = 2

        //加载失败
        const val FAIL = 3
    }
}