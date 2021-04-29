package com.hhda.andcommon.widget.squareview.v1

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * 正方形布局
 */
class SquareView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

}