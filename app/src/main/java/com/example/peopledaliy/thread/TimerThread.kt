package com.example.peopledaliy.thread

import android.os.Handler

class TimerThread:Thread {
    var handler:Handler?=null

    constructor(handler: Handler?) {
        this.handler = handler
    }

    override fun run() {
        super.run()
        Thread.sleep(1500)
        handler!!.sendEmptyMessage(0)
    }
}