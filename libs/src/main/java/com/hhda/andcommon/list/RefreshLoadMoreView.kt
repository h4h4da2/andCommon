package com.hhda.andcommon.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.CollectionUtils

import com.hhda.andcommon.databinding.AcViewRefreshLoadLayoutBinding
import com.hhda.andcommon.pageinfo.LoadAction
import com.hhda.andcommon.pageinfo.PageInfo
import com.scwang.smart.refresh.layout.SmartRefreshLayout


class RefreshLoadMoreView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    var enableRefresh: Boolean = true
    var enableLoadMore: Boolean = true

    private var mOnLoadLi: (() -> Unit)? = null
    private var mOnRefreshLi: (() -> Unit)? = null

    //空布局渲染
    private var mEmptyViewRender: ((View?) -> Unit)? = null

    //错误布局渲染
    private var mErrorViewRender: ((View?) -> Unit)? = null

    private var lm: RecyclerView.LayoutManager? = null

    private var binding: AcViewRefreshLoadLayoutBinding? = null

    init {
//        View.inflate(context, R.layout.ac_view_refresh_load_layout, this)
        binding = AcViewRefreshLoadLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setEmptyView(emptyLayout: Int, render: ((View?) -> Unit)? = null) {
        mEmptyViewRender = render
//        statusView.addEmptyStatus(emptyLayout, render)
    }

    fun setErrorView(errLayout: Int, render: ((View?) -> Unit)? = null) {
        mErrorViewRender = render
//        statusView.addStatus(IStatus.ERROR, errLayout, render)
    }

    fun initRecyclerView(lm: RecyclerView.LayoutManager) {
        binding?.recyclerView?.quickBindAdapter(lm)
    }

    fun initConfig() {
//        refreshLayout.setDefaultRefreshHeaderAndFooter(context)
//        refreshLayout.setOnRefreshListener { mOnRefreshLi?.invoke() }
//        refreshLayout.setOnLoadMoreListener { mOnLoadLi?.invoke() }
//        refreshLayout.setEnableRefresh(enableRefresh)
//        refreshLayout.setEnableLoadMore(enableLoadMore)
    }

//    fun getAdapter(): BaseCymBinderAdapter {
//        if (recyclerView.getQuickBinderAdapter() == null) {
//            recyclerView.quickBindAdapter(lm)
//        }
//        return recyclerView.getQuickBinderAdapter()!!
//    }
//
//    fun setData(data: List<Any>) {
//        recyclerView.setBindAdapterDiffData(data)
//    }

    fun handlePageInfo(pageInfo: PageInfo<*>) {
        val delayTime = 400
        when (pageInfo.pageIndex) {
            PageInfo.START_PAGE_INDEX - 1 -> {
                // do nothing
            }
            //加载第一屏幕数据
            PageInfo.START_PAGE_INDEX -> {
                //刷新 显示刷新的动画
                if (pageInfo.loadAction.step == LoadAction.LOADING) {
                    val isRefreshing = binding?.refreshLayout?.isRefreshing ?: false
                    if (!isRefreshing && pageInfo.enableRefreshAnimate) {
                        binding?.refreshLayout?.autoRefresh(0, 0, 0f, true)
                    }
                } else if (pageInfo.loadAction.step == LoadAction.FAIL) {
                    binding?.refreshLayout?.finishRefresh(delayTime, false, true)
                    doShowErr()
                } else if (pageInfo.loadAction.step == LoadAction.SUCCEED) {
                    binding?.refreshLayout?.finishRefresh(delayTime)
                }
                //
                if (pageInfo.loadAction.step == LoadAction.SUCCEED) {
                    val isEmpty = CollectionUtils.isEmpty(pageInfo.loadAction.data)
//                    pageInfo.loadAction.data
                    if (isEmpty) {
                        doShowEmpty()
                    } else {
                        doShowContent()
                    }
                }
            }
            else -> {
                if (pageInfo.loadAction.step == LoadAction.SUCCEED) {
                    binding?.refreshLayout?.finishLoadMore()
                } else if (pageInfo.loadAction.step == LoadAction.FAIL) {
//                    binding?.refreshLayout?.finishLoadMore(delayTime)
                    binding?.refreshLayout?.finishLoadMore(delayTime, false, true)
                }
            }
        }
        if (enableLoadMore) {
            binding?.refreshLayout?.setEnableLoadMore(pageInfo.canLoadMore)
        }
    }

    fun setRefreshListener(li: (() -> Unit)? = null) {
        this.mOnRefreshLi = li
    }

    fun setLoadMoreListener(li: (() -> Unit)? = null) {
        this.mOnLoadLi = li
    }


    private fun doShowEmpty() {
//        if (statusView.hasStatus(IStatus.EMPTY)) {
//            statusView.showEmpty(mEmptyViewRender)
//        }
    }

    private fun doShowContent() {
//        statusView.showContent()
    }

    private fun doShowErr() {
//        if (statusView.hasStatus(IStatus.ERROR)) {
//            statusView.showEmpty(mErrorViewRender)
//        }
    }

    private fun getRefreshLayout(): SmartRefreshLayout? {
        return binding?.refreshLayout
    }
}