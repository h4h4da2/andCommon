package com.hhda.pay.constants

enum class PayMethod(val type: Int, val desc: String) {

    ALI_PAY(1, "支付宝"),
    WX_PAY(2, "微信");
}