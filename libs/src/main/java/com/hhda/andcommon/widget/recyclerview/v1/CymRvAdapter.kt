package com.hhda.andcommon.widget.recyclerview.v1

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseBinderAdapter
import java.util.*


fun RecyclerView.quickInitAdapter(lm: RecyclerView.LayoutManager? = null): BaseBinderAdapter {
    layoutManager = lm ?: LinearLayoutManager(context)
    itemAnimator = null
    adapter = BaseBinderAdapter()
    return adapter as BaseBinderAdapter
}


fun RecyclerView.getBinderAdapter(): BaseBinderAdapter? {
    val tAdapter = this.adapter
    if (tAdapter is BaseBinderAdapter) {
        return tAdapter
    }
    return null
}


fun RecyclerView.setDiffData(dataList: Collection<Any>, isRefresh: Boolean) {

    var tempList = dataList
    if (tempList !is MutableList<Any>) {
        tempList = ArrayList(dataList)
    }
    if (isRefresh) {
        this.getBinderAdapter()?.getDiffer()?.submitList(tempList as MutableList<Any>) {
            layoutManager?.scrollToPosition(0)
        }
    } else {
        this.getBinderAdapter()?.setDiffNewData(tempList as MutableList<Any>)
    }

}