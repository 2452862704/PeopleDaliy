package com.example.peopledaliy.mvp.model

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.model.BaseModel
import com.example.peopledaliy.mvp.contract.UserContract
import io.reactivex.Observable

class UserModel:BaseModel(),UserContract.IUserModel {
    override fun requestUser(map: Map<String, Any>): Observable<BaseEntity>? {
        return null
    }
}