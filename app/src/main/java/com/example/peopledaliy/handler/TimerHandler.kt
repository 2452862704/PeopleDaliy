package com.example.peopledaliy.handler

import android.os.Handler
import android.os.Message
import java.lang.ref.WeakReference

class TimerHandler:Handler {
    private var weakReference: WeakReference<TimerCallBack>? = null

    constructor(callBack: TimerCallBack){
        weakReference = WeakReference(callBack)
    }

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        weakReference!!.get()!!.callBack()
    }

    interface TimerCallBack{
        fun callBack()
    }
}