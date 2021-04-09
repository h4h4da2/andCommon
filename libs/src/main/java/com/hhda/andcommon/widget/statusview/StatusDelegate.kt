package com.hhda.andcommon.widget.statusview

import android.view.View
import com.hhda.andcommon.widget.interfaces.IViewRender
import com.hhda.andcommon.widget.statusview.IStatus

data class StatusDelegate(
    val status: IStatus,
    val layout: Int,
    val apply: IViewRender? = null
)