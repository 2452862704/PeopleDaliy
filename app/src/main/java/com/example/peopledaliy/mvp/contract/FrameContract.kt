package com.example.peopledaliy.mvp.contract

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.model.IModel
import com.example.peopledaliy.mvp.base.view.IView
import io.reactivex.Observable

interface FrameContract {
    interface IFrameModel:IModel{
        fun request(map: Map<String, Any>): Observable<BaseEntity>?
    }
    interface IFrameView:IView{
        fun refresh(resultMap: Map<String,Any>)
    }
}