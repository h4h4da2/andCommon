package com.hhda.widget.edit

import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener

class EditUtil {


    var mEditText: EditText? = null
    var mClearBtn: View? = null
    var mShowPasswordBtn: View? = null
    private var isShowPassword = false

    private var hideClearBtnWhenInput: Boolean = true

    private var hideClearBtnRender: Consumer2<View, Boolean>? = null

    private var showPasswordRender: Consumer2<View, Boolean>? = null

    /**
     * 清除输入的icon
     */
    private var clearInputIcon = -1


    fun bindClearBtn(clearBtn: View, hideWhenInput: Boolean) {
        this.mClearBtn = clearBtn
        this.hideClearBtnWhenInput = hideClearBtnWhenInput
        mClearBtn?.setOnClickListener { setText("") }
        updateClearBtnWhenInput(getInputText())
    }


    fun bindEditText(editText: EditText) {
        this.mEditText = editText
        mEditText?.addTextChangedListener { str ->
            updateClearBtnWhenInput(str?.toString())
        }
    }

    fun setClearBtnVisible(show: Boolean) {
        when (!show) {
            true -> mClearBtn?.visibility = View.GONE
            else -> mClearBtn?.visibility = View.VISIBLE
        }
    }

    fun bindShowPasswordBtn(
        showPwdBtn: View,
        isShowPwd: Boolean = false,
        render: Consumer2<View, Boolean>? = null

    ) {
        this.mShowPasswordBtn = showPwdBtn
        this.isShowPassword = isShowPwd
        this.showPasswordRender = render

        this.mShowPasswordBtn?.setOnClickListener {
            isShowPassword = !isShowPassword
            updateShowPwsBtnWhenClick(isShowPassword)
        }
        updateShowPwsBtnWhenClick(isShowPassword)
    }

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

    private fun updateShowPwsBtnWhenClick(showPwd: Boolean) {
        showPasswordRender?.accept(mShowPasswordBtn, showPwd)
        when (showPwd) {
            true -> {
                mEditText?.showPassword()
            }
            else -> {
                mEditText?.hidePassword()
            }
        }
    }

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


    interface Consumer2<A, B> {
        fun accept(a: A?, b: B?)
    }
}


