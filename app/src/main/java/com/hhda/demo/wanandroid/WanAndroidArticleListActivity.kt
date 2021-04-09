package com.hhda.demo.wanandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hhda.andcommon.widget.recyclerview.page.IPage
import com.hhda.andcommon.widget.recyclerview.page.IPageLoader
import com.hhda.andcommon.widget.recyclerview.page.impl.CommonPage
import com.hhda.demo.R
import com.hhda.demo.databinding.ActivityWanAndroidArticleListBinding

/**
 * WanAndroid 首页文章列表页
 * 列表测试页面
 */
class WanAndroidArticleListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWanAndroidArticleListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        R.layout.activity_wan_android_article_list
        binding = ActivityWanAndroidArticleListBinding.inflate(layoutInflater)

        initViews()
        initData()

    }

    private fun initViews() {
        binding.rlView.setDefaultPageHandler(object : IPageLoader {
            override fun doLoad(page: IPage) {
                fetchData(page)
            }
        })
        binding.rlView.initConfig()
    }

    private fun initData() {
        binding.rlView.refresh()

    }

    fun fetchData(page: IPage) {
        if (page !is CommonPage) return


    }
}