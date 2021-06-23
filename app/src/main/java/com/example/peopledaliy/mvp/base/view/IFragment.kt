package com.example.peopledaliy.mvp.base.view

import android.os.Bundle

interface IFragment {
    fun bindLayout():Int
    fun initView()
    fun initData()
    fun inject()
    fun startPage(bundle: Bundle?, clazz: Class<*>?)
}