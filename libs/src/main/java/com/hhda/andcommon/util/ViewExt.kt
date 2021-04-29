package com.hhda.andcommon.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun View.ifShow(visibility: Boolean) {
    this.visibility = when (visibility) {
        true -> View.VISIBLE
        else -> View.GONE
    }
}

fun ViewGroup.inflateViewFromParent(@LayoutRes layoutId: Int, attach: Boolean): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attach)
}