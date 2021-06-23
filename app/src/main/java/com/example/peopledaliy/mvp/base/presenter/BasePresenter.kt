package com.example.peopledaliy.mvp.base.presenter

import com.blankj.utilcode.util.LogUtils
import com.example.peopledaliy.mvp.base.model.IModel
import com.example.peopledaliy.mvp.base.view.IView
import com.example.peopledaliy.mvp.base.model.BaseEntity
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open abstract class BasePresenter<M:IModel,V:IView>:IPresenter,Observer<BaseEntity> {

    var m:M;
    var v: V;
    protected var compositeDisposable: CompositeDisposable? = null
    constructor(m: M, v: V) {
        this.m = m
        this.v = v
        this.compositeDisposable = CompositeDisposable()
    }

    override fun onDestory() {
        compositeDisposable!!.dispose();
        compositeDisposable!!.clear();
    }

    override fun onSubscribe(d: Disposable) {
        compositeDisposable!!.add(d);
        v!!.showDialog();
    }


    override fun onError(e: Throwable) {
        LogUtils.i("onError:"+e.message)
        v!!.showMsg(e.message!!);
    }

    override fun onComplete() {
        v!!.hideDialog();
    }
}