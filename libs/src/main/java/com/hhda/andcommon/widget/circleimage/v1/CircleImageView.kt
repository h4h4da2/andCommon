package com.hhda.andcommon.widget.circleimage.v1

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.annotation.Px
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

/**
 * 圆角 image
 */
class CircleImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {


    fun render(imageUrl: String, @Px corner: Int, isCircle: Boolean) {
        render(Glide.with(this).load(imageUrl), corner, isCircle)
    }


    fun render(@DrawableRes imageUrl: Int, @Px corner: Int, isCircle: Boolean) {
        render(Glide.with(this).load(imageUrl), corner, isCircle)
    }

    private fun render(builder: RequestBuilder<Drawable>, @Px corner: Int, isCircle: Boolean) {
        builder.apply {
            when (isCircle) {
                true -> transform(CircleCrop())
                else -> transform(CenterCrop(), RoundedCorners(corner))
            }
        }
            .into(this)
    }
}