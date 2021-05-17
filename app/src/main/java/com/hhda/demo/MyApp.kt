package com.hhda.demo

import android.app.Application
import io.reactivex.plugins.RxJavaPlugins

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler {
            it.printStackTrace()
        }
    }
}