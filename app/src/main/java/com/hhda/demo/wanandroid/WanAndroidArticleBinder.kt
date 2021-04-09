package com.hhda.demo.wanandroid

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.BaseItemBinder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hhda.demo.R

class WanAndroidArticleBinder :
    BaseItemBinder<WanAndroidArticleBinder.ArticleItem, BaseViewHolder>() {


    override fun convert(holder: BaseViewHolder, data: ArticleItem) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_list_wandroid_acticle, parent, false)
        return BaseViewHolder(itemView)
    }

    data class ArticleItem(
        val apkLink: String,
        val author: String,
        val chapterId: Int,
        val chapterName: String,
        val collect: Boolean,
        val courseId: Int,
        val desc: String,
        val descMd: String,
        val envelopePic: String,
        val fresh: Boolean,
        val link: String,
        val niceDate: String,
        val niceShareDate: String,
        val projectLink: String,
        val publishTime: Long,
        val realSuperChapterId: Int,
        val shareUser: String,
        val superChapterId: Int,
        val superChapterName: String,
        val tags: List<Any>,
        val title: String,
        val type: Int,
        val userId: Int,
        val visible: Int,
        val zan: Int
    )
}