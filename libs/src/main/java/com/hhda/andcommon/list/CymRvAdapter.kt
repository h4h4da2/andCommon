package com.hhda.andcommon.list

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseBinderAdapter
import java.util.*


fun RecyclerView.quickBindAdapter(lm: RecyclerView.LayoutManager? = null): BaseBinderAdapter {
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


fun RecyclerView.setDiffData(dataList: Collection<Any>) {

    when (dataList) {
        is List<Any> -> {
            this.getBinderAdapter()?.setDiffNewData(ArrayList(dataList))
        }
        is MutableList<Any> -> {
            this.getBinderAdapter()?.setDiffNewData(dataList)
        }
    }
}