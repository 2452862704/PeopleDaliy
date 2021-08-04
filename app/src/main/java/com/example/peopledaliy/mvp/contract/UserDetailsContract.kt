package com.example.peopledaliy.mvp.contract

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.model.IModel
import com.example.peopledaliy.mvp.base.view.IView
import com.example.peopledaliy.mvp.model.entity.UploadFileEntity
import io.reactivex.Observable

interface UserDetailsContract {
    interface IUserDetailsModel : IModel {
        fun requestUser(map: Map<String, Any>): Observable<BaseEntity>
        fun updateUser(map: Map<String, Any>): Observable<BaseEntity>
        fun uploadHead(map: Map<String, Any>): Observable<UploadFileEntity>
    }

    interface IUserDetailsView : IView {
        fun refreshView(entity: BaseEntity)
    }
}