package com.hhda.widget.statusview.v1

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.*

/**
 * 多状态展示view
 * empty , loading ,succeed,error
 */
@Suppress("MemberVisibilityCanBePrivate")
class StatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), LifecycleObserver {


    private val statusManager: StatusManager by lazy {
        StatusManager()
    }

    fun addStatus(status: IStatus, layout: Int) = apply {
        statusManager.addStatus(StatusDelegate(status, layout, null))
    }


    fun hasStatus(status: IStatus): Boolean {
        return statusManager.hasStatus(status)
    }

    fun bindLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    /**
     * 遍历children 判断是否 含有 该status 对于的view
     *
     * 修改 所有children的状态
     * 返回当前  status 的view
     */
    fun setStatus(status: IStatus, apply: IViewRender? = null): View? {
        var curView = statusManager.getViewByStatus(status)
        if (curView == null) {
            curView = statusManager.createViewByStatus(status, this)
        }
        var isExist = false
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            if (childView === curView) {
                childView.visibility = View.VISIBLE
                isExist = true
            } else {
                childView.visibility = View.GONE
            }
        }
        if (!isExist) {
            addView(curView)
        }
        curView?.visibility = visibility
        curView?.let { apply?.render(curView) }

        return curView
    }

    fun showEmpty(apply: IViewRender? = null) {
        setStatus(status = IStatus.EMPTY, apply = apply)
    }


    fun showContent() {
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            if (statusManager.getStatusByView(childView) == null) {
                childView.visibility = View.VISIBLE
            } else {
                childView.visibility = View.GONE
            }
        }
    }

    fun addEmptyStatus(layout: Int) = apply {
        addStatus(IStatus.EMPTY, layout)
    }

    fun getViewByStatus(status: IStatus): View? {
        return statusManager.getViewByStatus(status)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        statusManager.release()
    }


}