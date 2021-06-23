package com.example.peopledaliy.mvp.presenter

import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.presenter.BasePresenter
import com.example.peopledaliy.mvp.contract.LoginContract
import com.example.peopledaliy.mvp.model.entity.ResponseUserEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class LoginPresenter  @Inject constructor(m:LoginContract.ILoginModel, v:LoginContract.ILoginView):BasePresenter<LoginContract.ILoginModel,LoginContract.ILoginView>(m, v) {
    fun requestLogin(phone:Long,pwd:String,code:Int){
        if (pwd.isEmpty()){
            return
        }
        val map: HashMap<String, Any> = HashMap()
        map.put("phone",phone)
        map.put("pwd",pwd)
        map.put("code",code)
        Log.e("fkt",""+map)
        Log.e("fkt",""+m)
        m.request(map)!!
             .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this)
    }
    override fun onNext(t: BaseEntity) {
        if (t is ResponseUserEntity){
            LogUtils.i("onNext:"+t.message)
            val responseUserEntity=t as ResponseUserEntity
//            if (responseUserEntity.getValues()!=null){
//
//            }
        }
        v.refreshView(t)
    }
}