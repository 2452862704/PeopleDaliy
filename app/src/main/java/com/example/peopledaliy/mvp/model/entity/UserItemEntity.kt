package com.example.peopledaliy.mvp.model.entity

class UserItemEntity(s: String, nothing: Nothing?) {
    var name: String? = null
    var selFlag = false
    var clazz: Class<*>? = null

    fun UserItemEntity(name: String?, clazz: Class<*>?) {
        this.name = name
        this.clazz = clazz
    }
}