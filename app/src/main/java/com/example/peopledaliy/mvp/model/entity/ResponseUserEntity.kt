package com.example.peopledaliy.mvp.model.entity

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.google.gson.Gson

class ResponseUserEntity : BaseEntity() {
    var values: String? = null
//    fun getValues(): UserEntity? {
//        return if (values!!.isEmpty()) null else Gson().fromJson(values, UserEntity::class.java)
//    }
}