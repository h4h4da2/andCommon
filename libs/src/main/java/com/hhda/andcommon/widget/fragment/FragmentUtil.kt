package com.hhda.andcommon.widget.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.blankj.utilcode.util.FragmentUtils
import com.blankj.utilcode.util.LogUtils

object FragmentUtil {

    private const val TAG = "FragmentUtil"

    const val ARG_TAG = "FragmentUtil_TAG"

    const val ARG_STATE = "FragmentUtil_STATE"

    const val TAG_PREFIX = "FragmentUtil_"

    const val FRAG_STATE_SHOW = 1001
    const val FRAG_STATE_HIDE = 1002


    const val OPERATE_ACTION_SHOW_OR_HIDE = "OPERATE_ACTION_SHOW_OR_HIDE"


    /**
     * 设置fragment tag
     */
    fun setFragmentTag(fragment: Fragment, tag: String?) {
        val b = fragment.arguments ?: Bundle()
        val realTag = tag ?: "${TAG_PREFIX}${fragment.javaClass.simpleName}"
        b.putString(ARG_TAG, realTag)
    }

    fun getFragmentTag(fragment: Fragment?): String? {
        return fragment?.arguments?.getString(ARG_TAG)
    }

    fun setFragmentState(fragment: Fragment, state: Int) {
        val b = fragment.arguments ?: Bundle()
        b.putInt(ARG_STATE, state)
    }


    ///////////////////////////////////////////////////////////////////////////
    // fragment 操作
    ///////////////////////////////////////////////////////////////////////////

    fun showOrHide(
        showIndex: Int,
        fragments: List<Fragment>,
        fm: FragmentManager,
        containerId: Int
    ) {
        if (showIndex >= fragments.size) {
            LogUtils.dTag(TAG, "out of range index:$showIndex")
            return
        }
        fragments.forEachIndexed { index, frag ->
            if (getFragmentTag(frag).isNullOrBlank()) {
                setFragmentTag(frag, null)
            }
            when (index == showIndex) {
                true -> setFragmentState(frag, FRAG_STATE_SHOW)
                else -> setFragmentState(frag, FRAG_STATE_HIDE)
            }
        }
        val ft = fm.beginTransaction()

        operate(
            operateAction = OPERATE_ACTION_SHOW_OR_HIDE,
            layoutContainerId = containerId,
            fm = fm,
            ft = ft,
            src = null,
            dest = fragments.toTypedArray()
        )
    }


    private fun operate(
        operateAction: String,
        layoutContainerId: Int,
        fm: FragmentManager,
        ft: FragmentTransaction,
        src: Fragment?,
        vararg dest: Fragment
    ) {
        if (src != null && src.isRemoving) {
            Log.e("FragmentUtils", src.javaClass.name + " is isRemoving")
            return
        }
        var name: String
        var args: Bundle?
        when (operateAction) {
            OPERATE_ACTION_SHOW_OR_HIDE -> {
                for (fragment in dest) {
                    args = fragment.arguments
                    if (args == null) return
                    name = args.getString(ARG_TAG, fragment.javaClass.name)
                    val fragState = args.getInt(ARG_STATE)
                    val fragmentByTag = fm.findFragmentByTag(name)

                    if (fragmentByTag == null && fragState == FRAG_STATE_SHOW) {
                        ft.add(layoutContainerId, fragment, name)
                    }
                    if (fragmentByTag != null && fragmentByTag.isAdded) {
                        when (fragState) {
                            FRAG_STATE_SHOW -> ft.show(fragmentByTag)
                            FRAG_STATE_HIDE -> ft.hide(fragmentByTag)
                        }
                    }
                }
            }
        }
        ft.commitAllowingStateLoss()
        fm.executePendingTransactions()
    }


}