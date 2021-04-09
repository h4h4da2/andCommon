package com.hhda.andcommon.widget.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseBinderAdapter
import com.hhda.andcommon.databinding.AcViewRefreshLoadLayoutBinding
import com.hhda.andcommon.widget.interfaces.IViewRender
import com.hhda.andcommon.widget.recyclerview.page.IPageHandler
import com.hhda.andcommon.widget.recyclerview.page.IPageLoader
import com.hhda.andcommon.widget.recyclerview.page.impl.CommonPageHandler
import com.hhda.andcommon.widget.statusview.IStatus


class RefreshLoadMoreView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    var enableRefresh: Boolean = true
    var enableLoadMore: Boolean = true

    //空布局渲染
    private var mEmptyViewRender: IViewRender? = null

    //错误布局渲染
    private var mErrorViewRender: IViewRender? = null

    private var lm: RecyclerView.LayoutManager? = null

    private var mPageHandler: IPageHandler? = null


    @Suppress("JoinDeclarationAndAssignment")
    lateinit var binding: AcViewRefreshLoadLayoutBinding

    init {
        binding = AcViewRefreshLoadLayoutBinding.inflate(LayoutInflater.from(context), this, false)
    }

    fun setEmptyView(emptyLayout: Int, render: IViewRender? = null) {
        mEmptyViewRender = render
        binding.statusView.addEmptyStatus(emptyLayout, render)
    }

    fun setErrorView(errLayout: Int, render: IViewRender? = null) {
        mErrorViewRender = render
        binding.statusView.addStatus(IStatus.ERROR, errLayout, render)
    }

    fun initRecyclerView(lm: RecyclerView.LayoutManager) {
        binding.recyclerView.quickInitAdapter(lm)
    }


    fun setDefaultPageHandler(pageLoader: IPageLoader) {
        this.mPageHandler = CommonPageHandler(this, pageLoader)
    }

    fun initConfig() {
        binding.refreshLayout.setDefaultRefreshHeaderAndFooter(context)
        binding.refreshLayout.setOnRefreshListener {
            mPageHandler?.onLoadStart()
        }
        binding.refreshLayout.setOnLoadMoreListener {
            mPageHandler?.onLoadStart()
        }
        binding.refreshLayout.setEnableRefresh(enableRefresh)
        binding.refreshLayout.setEnableLoadMore(enableLoadMore)
    }

    fun getAdapter(): BaseBinderAdapter {
        if (binding.recyclerView.getBinderAdapter() == null) {
            binding.recyclerView.quickInitAdapter(lm)
        }
        return binding.recyclerView.getBinderAdapter()!!
    }

    fun setData(data: List<Any>) {
        binding.recyclerView.setDiffData(data)
    }

    fun refresh() {
        mPageHandler?.onLoadStart()
    }

    fun onReqComplete(data: List<Any>?, err: Any?) {
        mPageHandler?.onLoadComplete(data, err)
    }

    fun showEmpty() {
        if (binding.statusView.hasStatus(IStatus.EMPTY)) {
            binding.statusView.showEmpty(mEmptyViewRender)
        }
    }

    fun showContent() {
        binding.statusView.showContent()
    }

    fun showErr(err: Any?) {
        if (binding.statusView.hasStatus(IStatus.ERROR)) {
//            binding.statusView.shower()
        }
    }


}