package com.hhda.demo

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PermissionUtils
import com.hhda.demo.databinding.ActivityRouterBinding
import com.hhda.demo.pagerdemo.PagerDemoActivity
import com.hhda.demo.wanandroid.WanAndroidArticleListActivity

class RouterActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRouterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRouterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        R.layout.activity_router

        initClicks()
        req()
    }

    private fun req() {

    }

    private fun initClicks() {
        binding.pageDemo.setOnClickListener { PagerDemoActivity.route(this) }
        binding.wanAndroid.setOnClickListener { WanAndroidArticleListActivity.route(this) }
    }


}