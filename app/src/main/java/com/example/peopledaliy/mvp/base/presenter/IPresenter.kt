package com.example.peopledaliy.mvp.base.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 使用lifecycle框架实现presenter下方法与activity或fragment生命周期绑定
 */
interface IPresenter : LifecycleObserver{
    @OnLifecycleEvent(value = Lifecycle.Event.ON_DESTROY)
    fun onDestory()
}