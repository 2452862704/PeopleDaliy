package com.example.peopledaliy.mvp.model.entity

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.google.gson.Gson

class ToKenEntity:BaseEntity() {
    var values: String? = null
    fun getValues(): Values? {
        return Gson().fromJson(values, Values::class.java)
    }
    class Values {
        var token: String? = null
    }
}