package com.example.peopledaliy.mvp.model.entity

class UserMenuEntity(resIcon7281: Int, s: String, nothing: Nothing?) {
    //图标本地资源ID
    var imgId = 0
    //名称
    var name: String? = null
    //目标activity Class
    var clazz: Class<*>? = null
    fun UserMenuEntity(imgId: Int, name: String?, clazz: Class<*>?) {
        this.imgId = imgId
        this.name = name
        this.clazz = clazz
    }
}