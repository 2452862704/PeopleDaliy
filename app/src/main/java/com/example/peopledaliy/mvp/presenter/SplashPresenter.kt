package com.example.peopledaliy.mvp.presenter


import androidx.annotation.NonNull
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.presenter.BasePresenter
import com.example.peopledaliy.mvp.contract.SplashContract
import com.example.peopledaliy.mvp.model.entity.ToKenEntity
import com.example.peopledaliy.utils.SpUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class SplashPresenter @Inject constructor(m1:SplashContract.ISplashModel,v1:SplashContract.ISplashView):BasePresenter<SplashContract.ISplashModel,SplashContract.ISplashView>(m1,v1) {

    //请求服务器token方法
    fun requestToken() {
        val map: MutableMap<String, Any> = HashMap()
//        if (ActivityCompat.checkSelfPermission(App.getInstance()!!.getApplicationContext(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            return
//        }
        //        String imie = PhoneUtils.getIMEI();
        map["imie"] = "" + System.currentTimeMillis()
        m.requestToken(map)!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this)
    }

    override fun onNext(@NonNull baseEntity: BaseEntity) {
        //请求成功逻辑校验
        val entity: ToKenEntity = baseEntity as ToKenEntity
        if (entity.status.equals("200")) {
            //保存token到本地SP存储中
            SpUtils().saveData("token", entity.getValues()!!.token)
            //通知activity界面跳转
            v.refresh()
        }
    }
}