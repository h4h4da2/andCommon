package com.hhda.andcommon.widget.statusview.v1

import android.view.LayoutInflater
import android.view.View

class StatusManager {


    var currentStatus: IStatus? = null
    private var statusDelegateList: ArrayList<StatusDelegate> = arrayListOf()
    private var viewCachePool: LinkedHashMap<IStatus, View> = LinkedHashMap()

    fun addStatus(sd: StatusDelegate) {
        val oldPos = statusDelegateList.indexOfFirst { delegate ->
            delegate.status == sd.status
        }
        when (oldPos < 0) {
            true -> statusDelegateList.add(sd)
            else -> {
                statusDelegateList[oldPos] = sd
            }
        }
    }

    fun hasStatus(status: IStatus): Boolean {
        val existStatus = statusDelegateList.firstOrNull { delegate -> delegate.status == status }
        return existStatus != null

    }

    fun getViewByStatus(state: IStatus): View? {
        return viewCachePool[state]
    }

    fun createViewByStatus(state: IStatus, parent: StatusView): View? {

        val item = statusDelegateList.find { it.status == state }
        val layoutRes = item?.layout ?: -1
        val oper = item?.apply
        if (layoutRes < 0) {
            return null
        }
        val createdView = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        viewCachePool[state] = createdView
        oper?.render(createdView, null)

        return createdView

    }

    fun getStatusByView(view: View): IStatus? {
        return viewCachePool.entries.find { it.value === view }?.key
    }


    fun release() {
        statusDelegateList.clear()
        viewCachePool.clear()
    }
}