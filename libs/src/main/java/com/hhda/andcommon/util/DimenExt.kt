package com.hhda.andcommon.util

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.StringUtils

fun dp2px(dpNum: Number): Int {
    return ConvertUtils.dp2px(dpNum.toFloat())
}

fun getColorRes(@ColorRes res: Int): Int {
    return ColorUtils.getColor(res)
}

fun getStringRes(@StringRes res: Int): String {
    return StringUtils.getString(res)
}
