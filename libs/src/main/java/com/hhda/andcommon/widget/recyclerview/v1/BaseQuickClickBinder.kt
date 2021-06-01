package com.hhda.andcommon.widget.recyclerview.v1

import android.view.View
import androidx.annotation.CallSuper
import com.chad.library.adapter.base.binder.BaseItemBinder
import com.chad.library.adapter.base.viewholder.BaseViewHolder

abstract class BaseQuickClickBinder<T, VH : BaseViewHolder> : BaseItemBinder<T, VH>() {

    //itemView 本身
    private var clickInfo = ClickInfo()

    //child
    private var childClickInfo = ClickInfo()

    @CallSuper
    override fun onChildClick(holder: VH, view: View, data: T, position: Int) {
        if (childClickInfo.pos == position && childClickInfo.lastClickId == view.id && System.currentTimeMillis() - childClickInfo.lastClickTime < 500L) {
            return
        }
        childClickInfo.lastClickId = view.id
        childClickInfo.lastClickTime = System.currentTimeMillis()
        childClickInfo.pos = position

        onChildQuickClick(holder, view, data, position)
    }


    @CallSuper
    override fun onClick(holder: VH, view: View, data: T, position: Int) {
        if (clickInfo.pos == position && clickInfo.lastClickId == view.id && System.currentTimeMillis() - clickInfo.lastClickTime < 500L) {
            return
        }
        clickInfo.lastClickId = view.id
        clickInfo.lastClickTime = System.currentTimeMillis()
        clickInfo.pos = position

        onQuickClick(holder, view, data, position)

    }

    open fun onQuickClick(holder: VH, view: View, data: T, position: Int) {}

    open fun onChildQuickClick(holder: VH, view: View, data: T, position: Int) {}

    private class ClickInfo(
        var lastClickTime: Long = -1L,
        var lastClickId: Int = -1,
        var pos: Int = -1
    )

}