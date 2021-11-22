package com.hhda.demo.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.hhda.demo.R
import com.hhda.demo.databinding.ActivityRouterBinding
import com.hhda.demo.pagerdemo.PagerDemoActivity
import com.hhda.demo.wanandroid.WanAndroidArticleListActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

class RouterActivity : AppCompatActivity() {

    val TAG = "Router"


    private lateinit var binding: ActivityRouterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRouterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        R.layout.activity_router

        initClicks()
        lifecycleScope.launch {
            val consume = measureTimeMillis {
                val t1 = async(Dispatchers.IO) { test1() }
                val t2 = async(Dispatchers.IO) { test2() }

                LogUtils.d("test", t1.await() + t2.await())

            }

            LogUtils.d("Route", "consume $consume")


        }
//        req()
    }

    private suspend fun getIntFunc(): Int = withContext(Dispatchers.IO) {
        LogUtils.d("test", " current ${Thread.currentThread().name}")
        1
    }

    private fun req() {

        runBlocking {
            flow<Int> { emit(getIntFunc()) }
                .flowOn(Dispatchers.IO)
                .collect {

                    LogUtils.d("test", " current item $it ${Thread.currentThread().name}")
                }
        }



        if (true) return

        GlobalScope.launch(Dispatchers.Main) {

            val r1 = mock()

            val r2 = mock()
            val result = r1 + r2
        }

        flow<Int> {

        }


        runBlocking {
            launch {
                delay(1000)
                LogUtils.d(TAG, "my job")
            }

//            val r1 = async { "" }.await()

//            val r2 = async { "" }.await()

            launch(Dispatchers.IO) { }

            withContext(Dispatchers.IO) {}


            coroutineScope {
//                launch {
                delay(10000)
                LogUtils.d(TAG, "my job2")
//                }

                delay(5000)
                LogUtils.d(TAG, "hello world")
            }

            withContext(Dispatchers.IO) {

            }

            LogUtils.d(TAG, "welcome")
        }

        LogUtils.d(TAG, "finish")


        if (true) return

        runBlocking {
            flow<Int> {
                emit(1)
            }
                .flowOn(Dispatchers.IO)
                .map { it -> it }
                .onEach { }

                .collect { }
        }


        runBlocking(Dispatchers.Main) {
            launch {

                delay(200L)
            }
            async { }

            coroutineScope {
                delay(1000)
            }
            val elapsedTime = measureTimeMillis {
                LogUtils.d("test", "this is first ${Thread.currentThread().name}")
                delay(200)
                LogUtils.d("test", "this is second  ${Thread.currentThread().name}")

                val r1 = async(newSingleThreadContext("test2")) {
                    LogUtils.d("test", "do test1")
                    test1()
                }
                val r2 = async(newSingleThreadContext("test1")) {
                    LogUtils.d("test", "do test2")
                    test2()
                }
                val r3 = withContext(Dispatchers.IO) { test1() }
                val r4 = async { test1() }.await()


                val tt = r3 + r4

//                LogUtils.d("test", "result ${r1.await() + r2.await()}")

//                coroutineScope { }
//                withContext(Dispatchers.IO) {}
                launch { }
                val result = r1.await() + r2.await()
                LogUtils.d("test result", result)
            }
            LogUtils.d("test total time", elapsedTime)


        }

        LogUtils.d("test", "not in coroutine")


    }


    private suspend fun test1(): String {
        LogUtils.d("test thread1", Thread.currentThread().name)
        val startTime = System.currentTimeMillis()
        while (true) {
            if (System.currentTimeMillis() - startTime >= 2000) {
                return "1"
            }
        }
        return "1"
    }

    private suspend fun test2(): String {
        LogUtils.d("test thread2", Thread.currentThread().name)
        val startTime = System.currentTimeMillis()
        while (true) {
            if (System.currentTimeMillis() - startTime >= 2500) {
                return "2"
            }
        }
        return "2"
    }

    private suspend fun mock(): String = withContext(Dispatchers.IO) {
        ""
    }


    private fun initClicks() {
        binding.pageDemo.setOnClickListener { PagerDemoActivity.route(this) }
        binding.wanAndroid.setOnClickListener { WanAndroidArticleListActivity.route(this) }
    }


}