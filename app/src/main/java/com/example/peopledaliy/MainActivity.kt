package com.example.peopledaliy

import android.content.Intent
import android.os.Handler
import android.os.Message
import android.view.View
import com.example.peopledaliy.mvp.base.view.BaseAcyivity
import com.example.peopledaliy.mvp.contract.SplashContract
import com.example.peopledaliy.mvp.di.DaggerSplashComponent
import com.example.peopledaliy.mvp.di.SplashModules
import com.example.peopledaliy.mvp.presenter.SplashPresenter
import com.example.peopledaliy.mvp.view.FrameActivity

class MainActivity : BaseAcyivity<SplashPresenter>(),SplashContract.ISplashView {
    val HANDLER_MSG =1
    val handler = Handler(object : Handler.Callback {
        override fun handleMessage(msg: Message): Boolean {
            if (msg.what==HANDLER_MSG){
                startActivity(Intent(this@MainActivity, FrameActivity::class.java))
            }
            return false
        }
    })
    override fun addStatusView(): View? {
        return f(R.id.splash_linear)
    }

    override fun bindLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData() {
        p!!.requestToken()

    }

    override fun inject() {
        DaggerSplashComponent.builder().splashModules(SplashModules(this)).build().inject(this)
    }

    override fun refresh() {
//        TimerThread(TimerHandler(this)).start()
        handler.sendEmptyMessageDelayed(HANDLER_MSG,1500)
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
        handler.removeCallbacksAndMessages(null)
    }

//    override fun callBack() {
//        startPage(FirstActivity::class.java)
//        this.finish()
//    }
}
