package com.hhda.andcommon.widget.statusview

interface IStatus {
    object SHOW_CONTENT

    object ERROR : IStatus

    object FAILED : IStatus

    object SUCCEED : IStatus

    object EMPTY : IStatus


}