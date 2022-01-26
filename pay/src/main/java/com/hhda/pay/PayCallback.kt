package com.hhda.pay

import com.hhda.pay.constants.PayMethod

interface PayCallback {

    fun onPay(type: PayMethod, code: Int, result: Any?)
}