package com.example.peopledaliy.mvp.base.model

import okhttp3.RequestBody

interface IModel {
    fun createBody(map: Map<String,Any>):RequestBody
}