package com.example.peopledaliy.mvp.model

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.model.BaseModel
import com.example.peopledaliy.mvp.contract.SplashContract
import com.example.peopledaliy.mvp.model.entity.ToKenEntity
import com.example.peopledaliy.network.Api
import com.example.peopledaliy.network.ChangeFunction
import com.example.peopledaliy.network.HttpFactory
import com.example.peopledaliy.network.HttpType
import io.reactivex.Observable
import javax.inject.Inject

class SplashModel  @Inject constructor():BaseModel(), SplashContract.ISplashModel{
    override fun requestToken(map: Map<String, Any>): Observable<BaseEntity> {
        val ob: Observable<ToKenEntity> = HttpFactory.instance!!
            .factory(HttpType.SIGNTYPE)!!
            .getRetrofit()!!.create(Api::class.java)
            .requestToken(createBody(map))

        return ob.map<BaseEntity>(ChangeFunction<ToKenEntity>())!!
    }
}