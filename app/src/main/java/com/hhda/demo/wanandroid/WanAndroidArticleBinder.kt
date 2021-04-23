package com.hhda.demo.wanandroid

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.BaseItemBinder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hhda.demo.R
import com.hhda.demo.dto.ArticleDTO

class WanAndroidArticleBinder :
    BaseItemBinder<ArticleDTO, BaseViewHolder>() {


    override fun convert(holder: BaseViewHolder, data: ArticleDTO) {
        holder.setText(R.id.tvTile, data.chapterName)
        holder.setText(R.id.tvUser, data.author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_list_wandroid_acticle, parent, false)
        return BaseViewHolder(itemView)
    }

}