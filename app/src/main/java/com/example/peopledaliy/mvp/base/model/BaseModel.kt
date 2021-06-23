package com.example.peopledaliy.mvp.base.model

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody

open class BaseModel:IModel {
    override fun createBody(map: Map<String, Any>): RequestBody {
        if (map==null){
            return null!!
        }
        return RequestBody.create(MediaType.parse("application/json"),Gson().toJson(map))
    }
}