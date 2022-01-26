package com.hhda.widget.statusview.v1

import android.view.View

data class StatusDelegate(
    val status: IStatus,
    val layout: Int,
    val view: View?
)