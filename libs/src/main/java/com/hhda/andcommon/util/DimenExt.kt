package com.hhda.andcommon.util

import com.blankj.utilcode.util.ConvertUtils

fun dp2px(dpNum: Number): Int {
    return ConvertUtils.dp2px(dpNum.toFloat())
}
