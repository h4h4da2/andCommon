package com.hhda.andcommon.pageinfo


/**
 * 0-> Succ
 * 1-> Loading
 * 1-> Succ
 */

data class PageInfo<T>(
    val hasMore: Boolean = true,
    val pageIndex: Int = START_PAGE_INDEX - 1,
    val pageSize: Int = 20,
    val loadAction: LoadAction<List<T>> = LoadAction(LoadAction.UN_LOAD, null),
    val enableRefreshAnimate: Boolean = true
) {

    companion object {
        const val START_PAGE_INDEX = 1
    }

//    fun reduce(data: Async<List<T>>): PageInfo<T> {
//        return when (data) {
//            is Success -> {
//                val list = data()
//                this.copy(
//                    hasMore = list.size >= pageSize,
//                    asyncData = data,
//                    pageIndex = pageIndex,
//                    enableRefreshAnimate = true
//                )
//            }
//            is Fail -> {
//                this.copy(
//                    hasMore = true,
//                    asyncData = data,
//                    enableRefreshAnimate = true
//                )
//            }
//            is Loading -> this.copy(
//                asyncData = data,
//                pageIndex = pageIndex + 1
//            )
//            else -> this
//        }
//    }
//
//    fun handleResult(data: Async<List<T>>, oldList: List<T>): List<T> {
//        return when (data) {
//            is Success -> {
//                val list = data()
//                when (pageIndex == START_PAGE_INDEX) {
//                    true -> list
//                    else -> oldList + list
//                }
//            }
//            else -> oldList
//        }
//    }

    val canLoadMore: Boolean
        get() = hasMore && !isUnloaded()

    /**
     * 没有加载过数据
     * @return Boolean
     */
    fun isUnloaded(): Boolean {
        return pageIndex == START_PAGE_INDEX - 1
    }


}