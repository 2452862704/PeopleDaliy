package com.example.peopledaliy.mvp.model

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.model.BaseModel
import com.example.peopledaliy.mvp.contract.LoginContract
import com.example.peopledaliy.mvp.model.entity.ResponseUserEntity
import com.example.peopledaliy.network.Api
import com.example.peopledaliy.network.ChangeFunction
import com.example.peopledaliy.network.HttpFactory
import com.example.peopledaliy.network.HttpType
import io.reactivex.Observable
import javax.inject.Inject

class LoginModel @Inject constructor():BaseModel(),LoginContract.ILoginModel {
    override fun request(map: HashMap<String, Any>): Observable<BaseEntity>? {
        val code = map.get("code") as Int
        map.remove("code")
        if (code==HttpCode.LOGINCODE){
            val body = createBody(map)
            val ob =
                HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!.getRetrofit()!!
                    .create(Api::class.java).requestLogin(body)
            return ob.map(ChangeFunction<ResponseUserEntity>())
        }else if(code==HttpCode.REGISTCODE){
            val body = createBody(map)
            val ob =
                HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!.getRetrofit()!!
                    .create(Api::class.java).requestRegister(body)
            return ob.map(ChangeFunction<ResponseUserEntity>())
        }
        return null
    }
}