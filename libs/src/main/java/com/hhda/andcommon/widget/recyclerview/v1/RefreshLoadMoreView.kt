package com.hhda.andcommon.widget.recyclerview.v1

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.arch.core.util.Function
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseBinderAdapter
import com.hhda.andcommon.databinding.AcViewRefreshLoadLayoutBinding
import com.hhda.andcommon.widget.statusview.v1.IViewRender
import com.hhda.andcommon.widget.recyclerview.v1.page.IPageHandler
import com.hhda.andcommon.widget.recyclerview.v1.page.IPageManager
import com.hhda.andcommon.widget.recyclerview.v1.page.impl.CommonPageHandler
import com.hhda.andcommon.widget.statusview.v1.IStatus


class RefreshLoadMoreView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    private var mEnableRefresh: Boolean = true
    private var mEnableLoadMore: Boolean = true

    //空布局渲染
    private var mEmptyViewRender: ViewRender? = null

    //错误布局渲染
    private var mErrorViewRender: ViewRender? = null

    //如果不设置 layoutManager 默认使用LinearLayoutManager
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    private var mPageHandler: IPageHandler? = null


    @Suppress("JoinDeclarationAndAssignment")
    lateinit var binding: AcViewRefreshLoadLayoutBinding

    init {
        binding = AcViewRefreshLoadLayoutBinding.inflate(
            LayoutInflater.from(context), this, true
        )
        binding.refreshLayout.setOnRefreshListener {
            mPageHandler?.onLoadStart(true)
        }
        binding.refreshLayout.setOnLoadMoreListener {
            mPageHandler?.onLoadStart(false)
        }
        //设置刷新头和loadMore 样式
        binding.refreshLayout.setDefaultRefreshHeaderAndFooter(context)
        //初始阶段不允许 加载更多
        binding.refreshLayout.setEnableLoadMore(false)

    }

    fun getEnableRefreshFlag(): Boolean {
        return mEnableRefresh
    }

    fun getEnableLoadMoreFlag(): Boolean {
        return mEnableLoadMore
    }

    fun setEmptyView(emptyLayout: Int, render: ViewRender? = null) {
        mEmptyViewRender = render
        binding.statusView.addEmptyStatus(emptyLayout)
    }

    fun setErrorView(errLayout: Int, render: ViewRender? = null) {
        mErrorViewRender = render
        binding.statusView.addStatus(IStatus.ERROR, errLayout)
    }

    fun setLayoutManger(lm: RecyclerView.LayoutManager) {
        this.mLayoutManager = lm
        binding.recyclerView.quickInitAdapter(mLayoutManager)
    }


    fun setDefaultPageHandler(pageManager: IPageManager) {
        this.mPageHandler = CommonPageHandler(this, pageManager)
    }


    fun setRefreshEnable(enable: Boolean) {
        this.mEnableRefresh = enable
        binding.refreshLayout.setEnableRefresh(mEnableRefresh)
    }

    fun setLoadMoreEnable(enable: Boolean) {
        this.mEnableLoadMore = enable
    }

    fun getAdapter(): BaseBinderAdapter {
        if (binding.recyclerView.getBinderAdapter() == null) {
            binding.recyclerView.quickInitAdapter(mLayoutManager)
        }
        return binding.recyclerView.getBinderAdapter()!!
    }

    fun setData(data: List<Any>, needScroll2Top: Boolean) {
        binding.recyclerView.setDiffData(data, needScroll2Top)
    }


    fun refresh() {
        binding.refreshLayout.autoRefresh()
        binding.refreshLayout.setEnableLoadMore(false)
    }

    fun onReqComplete(result: Any?, err: Any?) {
        binding.refreshLayout.setEnableLoadMore(mEnableLoadMore)
        mPageHandler?.onLoadComplete(result, err)
    }

    fun showEmpty() {
        if (binding.statusView.hasStatus(IStatus.EMPTY)) {
            binding.statusView.showEmpty(object : IViewRender {
                override fun render(view: View) {
                    mEmptyViewRender?.render(view, null)
                }
            })
        }
    }

    fun showContent() {
        binding.statusView.showContent()
    }

    fun showErr(err: Any?) {
        if (binding.statusView.hasStatus(IStatus.ERROR)) {
            binding.statusView.setStatus(IStatus.ERROR, object : IViewRender {
                override fun render(view: View) {
                    mErrorViewRender?.render(view, err)
                }
            })
        }
    }

    fun onDataChange(reduce: Function<List<Any>, List<Any>>) {
        mPageHandler?.onDataChange(reduce)
    }

    interface ViewRender {
        fun render(view: View, data: Any?)
    }


}