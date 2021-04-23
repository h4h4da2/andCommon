package com.hhda.andcommon.widget.recyclerview.page

interface IPage {

    fun getCurrentPageKey(): Any?

    fun nextPage(): IPage?


    fun isFirstPage(): Boolean

}