package com.hhda.andcommon.widget.statusbar

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

class StatusBarWrapView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var statusBarHeight = -1

    init {
        if (statusBarHeight < 0) {
            statusBarHeight = QMUIStatusBarHelper.getStatusbarHeight(context)
            setPadding(0, statusBarHeight, paddingRight, paddingBottom)
        }
    }


}