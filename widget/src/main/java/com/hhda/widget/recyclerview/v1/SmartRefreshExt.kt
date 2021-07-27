package com.hhda.widget.recyclerview.v1

import android.content.Context
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

fun SmartRefreshLayout.setDefaultRefreshHeaderAndFooter(context: Context) {
    setRefreshHeader(ClassicsHeader(context))
    setRefreshFooter(ClassicsFooter(context))
}

