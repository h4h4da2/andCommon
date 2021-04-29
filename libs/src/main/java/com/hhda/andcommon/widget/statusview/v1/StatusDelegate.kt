package com.hhda.andcommon.widget.statusview.v1

import com.hhda.andcommon.widget.interfaces.IViewRender

data class StatusDelegate(
    val status: IStatus,
    val layout: Int,
    val apply: IViewRender? = null
)