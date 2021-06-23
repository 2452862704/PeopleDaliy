package com.example.peopledaliy.mvp.model

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.model.BaseModel
import com.example.peopledaliy.mvp.contract.LREContract
import com.example.peopledaliy.network.HttpImpl
import com.example.peopledaliy.network.HttpType
import com.example.peopledaliy.network.HttpType.*
import io.reactivex.Observable
import javax.inject.Inject


class LREModel :BaseModel() , LREContract.ILREModel {



    override fun requestAll(vararg maps: MutableMap<String?, Any?>?): Observable<BaseEntity?>? {
        //根据传递进来的maps 数量创建生成专门用来存放被观察者的集合
        //根据传递进来的maps 数量创建生成专门用来存放被观察者的集合
        val observables: MutableList<Observable<BaseEntity?>?> = ArrayList()
        for (i in 0 until maps.size) {
            val map: MutableMap<String?, Any?>? = maps[i]
            observables.add(change2BaseEntity(map!!))
        }
        return Observable.merge(observables)
    }


    private val upload: HttpImpl? = null
    private  var sign:HttpImpl? = null
    private  var tokenSign: HttpImpl? = null
    private  var token:HttpImpl? = null
    private fun change2BaseEntity(map: MutableMap<String?, Any?>): Observable<BaseEntity?>? {
        val code = map["code"] as Int
        map.remove("code")
        var ob: Observable<BaseEntity?>? = null


        when (code) {


//             HttpCode.->{
//            if (sign == null)
//                sign = new SignRetrofit.Build().build();
//            http = sign;
//            }

        }
        return ob
    }

    override fun request_refresh_load(map: MutableMap<String?, Any?>?): Observable<BaseEntity?>? {
        return change2BaseEntity(map!!)
    }
}