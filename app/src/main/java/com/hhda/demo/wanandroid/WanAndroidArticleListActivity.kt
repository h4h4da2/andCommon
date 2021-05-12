package com.hhda.demo.wanandroid

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.util.Function
import com.hhda.andcommon.widget.recyclerview.v1.RefreshLoadMoreView
import com.hhda.demo.R
import com.hhda.demo.databinding.ActivityWanAndroidArticleListBinding
import com.hhda.demo.network.NetClient
import com.hhda.demo.wanandroid.page.WanAndroidPage
import com.hhda.demo.wanandroid.page.WanAndroidPageManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
        setContentView(binding.root)

        initViews()
        initData()
        binding.refreshBtn.setOnClickListener {
            binding.rlView.refresh()
        }


    }

    private fun initViews() {

        with(binding) {
            rlView.getAdapter().addItemBinder(WanAndroidArticleBinder())
            rlView.setDefaultPageHandler(WanAndroidPageManager(::fetchData))
            removeBtn.setOnClickListener { removeOper() }
        }

        binding.rlView.setEmptyView(R.layout.view_wanandorid_list_empty,
            object : RefreshLoadMoreView.ViewRender {
                override fun render(view: View, data: Any?) {
                    view.setOnClickListener {
                        binding.rlView.refresh()
                    }
                }
            }
        )

//        binding.rlView.showEmpty()
    }

    private fun removeOper() {
        binding.rlView.onDataChange(object : Function<List<Any>, List<Any>> {
            override fun apply(input: List<Any>?): List<Any> {
                if (input.isNullOrEmpty()) return emptyList()
                val newList = input.toMutableList()
                newList.removeAt(0)
                return newList
            }
        })
    }

    private fun initData() {
//        binding.rlView.refresh()
    }

    private fun fetchData(page: Any) {
        if (page !is WanAndroidPage) return
        val subscribe = NetClient.getArticleService().getHomePageArticle(page.curPage)
            .subscribeOn(Schedulers.io())
            .map { it.data!! }
            .map {
                it.apply {
                    if (curPage >= 5) over = true
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.rlView.onReqComplete(it, null)
            }


    }
}