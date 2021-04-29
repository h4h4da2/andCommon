package com.hhda.demo.wanandroid

import android.view.ViewGroup
import com.chad.library.adapter.base.binder.BaseItemBinder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hhda.andcommon.util.dp2px
import com.hhda.andcommon.util.ifShow
import com.hhda.andcommon.util.inflateViewFromParent
import com.hhda.andcommon.widget.circleimage.v1.CircleImageView
import com.hhda.demo.R
import com.hhda.demo.dto.ArticleDTO

class WanAndroidArticleBinder :
    BaseItemBinder<ArticleDTO, BaseViewHolder>() {


    override fun convert(holder: BaseViewHolder, data: ArticleDTO) {
        holder.setText(R.id.tvTile, data.title)
        val shareUserTxt = when (data.shareUser.isNullOrBlank()) {
            true -> "未知"
            else -> data.shareUser
        }
        holder.setText(R.id.tvUser, "分享者：${shareUserTxt}")
        val thumbView = holder.getView<CircleImageView>(R.id.imThumb)
        thumbView.ifShow(!data.envelopePic.isNullOrBlank())
        data.envelopePic?.let {
            thumbView.render(it, dp2px(4), false)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val itemView = parent.inflateViewFromParent(R.layout.view_list_wandroid_acticle, false)
        return BaseViewHolder(itemView)
    }

}