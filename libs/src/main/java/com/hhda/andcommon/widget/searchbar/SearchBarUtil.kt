package com.hhda.andcommon.widget.searchbar

import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ImageView

class SearchBarUtil {


    var mEditText: EditText? = null
    var mClearBtn: View? = null
    var mShowPasswordBtn: View? = null
    private var isShowPassword = true

    private var hideClearBtnWhenInput: Boolean = true

    /**
     * 隐藏和显示密码的icon
     */
    private var showPasswordIcon = -1
    private var hidePasswordIcon = -1

    /**
     * 清除输入的icon
     */
    private var clearInputIcon = -1

//    init {
//        mEditText?.addTextChangedListener(object : SimpleTextWatcher() {
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                updateClearBtnWhenInput(s.toString())
//            }
//        })
//
//        mClearBtn?.setOnClickListener {
//            mEditText?.setText("")
//            mClearBtn?.visibility = View.GONE
//        }
//        mShowPasswordBtn?.setOnClickListener {
//            mShowPasswordBtn
//        }
//        updateClearBtnWhenInput(getInputText())
////        updateShowPwsBtnWhenClick(isShowPassword)
//    }

    fun bindClearBtn(clearBtn: View, hideWhenInput: Boolean) {
        this.mClearBtn = clearBtn
        this.hideClearBtnWhenInput = hideClearBtnWhenInput
        mClearBtn?.setOnClickListener { setText("") }
        updateClearBtnWhenInput(getInputText())
    }

    fun setClearBtnVisible(show: Boolean) {
        when (!show) {
            true -> mClearBtn?.visibility = View.GONE
            else -> mClearBtn?.visibility = View.VISIBLE
        }
    }

//    fun bindShowPasswordBtn(
//        showPwdBtn: ImageView,
//        showPwdRes: Int,
//        hidePwdRes: Int,
//        isShowPwd: Boolean = false
//    ) {
//        this.mShowPasswordBtn = showPwdBtn
//        this.isShowPassword = isShowPwd
//        this.showPasswordIcon = showPwdRes
//        this.hidePasswordIcon = hidePwdRes
//
//        this.mShowPasswordBtn?.setOnClickListener {
//            isShowPassword = !isShowPassword
//            updateShowPwsBtnWhenClick(isShowPassword)
//        }
//        updateShowPwsBtnWhenClick(isShowPassword)
//    }

    fun setText(text: String?) {
        mEditText?.setText(text)
        text ?: return
        mEditText?.setSelection(text.length)
    }

    fun clearText() {
        setText(null)
    }

    fun getInputText(): String {
        return mEditText?.text?.toString() ?: ""
    }

    fun getEditText(): EditText? {
        return mEditText
    }

    private fun updateClearBtnWhenInput(input: String?) {
        when (input.isNullOrEmpty() && hideClearBtnWhenInput) {
            true -> mClearBtn?.visibility = View.GONE
            else -> mClearBtn?.visibility = View.VISIBLE
        }
    }

//    private fun updateShowPwsBtnWhenClick(showPwd: Boolean) {
//        when (showPwd) {
//            true -> {
//                mShowPasswordBtn?.setImageResource(showPasswordIcon)
//                mEditText.showPassword()
//            }
//            else -> {
//                mShowPasswordBtn?.setImageResource(hidePasswordIcon)
//                mEditText.hidePassword()
//            }
//        }
//    }

    private fun EditText.showPassword() {
        inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        val length = text.length
        setSelection(length)
    }

    private fun EditText.hidePassword() {
        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        val length = text.length
        setSelection(length)
    }

}


