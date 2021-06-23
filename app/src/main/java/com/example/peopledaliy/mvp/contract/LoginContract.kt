package com.example.peopledaliy.mvp.contract

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.model.IModel
import com.example.peopledaliy.mvp.base.view.IView
import io.reactivex.Observable

interface LoginContract {

    interface ILoginModel : IModel {
        fun request(map: HashMap<String, Any>): Observable<BaseEntity>? }

    interface ILoginView : IView {
        fun refreshView(entity: BaseEntity)
    }

}