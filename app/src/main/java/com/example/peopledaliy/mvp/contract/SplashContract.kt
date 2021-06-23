package com.example.peopledaliy.mvp.contract

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.model.IModel
import com.example.peopledaliy.mvp.base.view.IView
import io.reactivex.Observable


interface SplashContract {
    interface ISplashModel : IModel {
        fun requestToken(map: Map<String, Any>): Observable<BaseEntity>
    }

    interface ISplashView : IView {
        fun refresh()
    }
}