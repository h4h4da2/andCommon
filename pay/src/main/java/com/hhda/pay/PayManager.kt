package com.hhda.pay

import android.app.Activity
import android.os.Handler
import android.os.Looper
import com.alipay.sdk.app.PayTask
import com.hhda.pay.alipay.PayResult
import com.hhda.pay.constants.PayCode
import com.hhda.pay.constants.PayMethod
import java.util.concurrent.atomic.AtomicBoolean

class PayManager {

    //是否正在支付的标志位
    private val isPaying: AtomicBoolean = AtomicBoolean(false)

    fun aliPay(orderInfo: String, callback: PayCallback?, activity: Activity) {

        if (isPaying.get()) return
        isPaying.set(true)

        val payRunnable = Runnable {
            val alipay = PayTask(activity)
            val rowResult = alipay.payV2(orderInfo, true)

            val payResult = PayResult(rowResult)

            val payCode = when (payResult.resultStatus) {
                "9000" -> PayCode.USER_PAY_SUCCEED
                "6001" -> PayCode.USER_PAY_CANCEL
                else -> PayCode.PAY_FAIL
            }
            Handler(Looper.getMainLooper()).post {
                callback?.onPay(PayMethod.ALI_PAY, payCode, payResult)
            }
            isPaying.set(false)
        }

        val payThread = Thread(payRunnable)
        payThread.start()
    }

    fun wechatPay() {

    }


    fun pay(type: Int) {

    }
}