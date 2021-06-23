package com.example.peopledaliy.mvp.model

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.model.BaseModel
import com.example.peopledaliy.mvp.contract.FrameContract
import io.reactivex.Observable

class FrameModel:BaseModel(),FrameContract.IFrameModel {
    override fun request(map: Map<String, Any>): Observable<BaseEntity>? {
        return null
    }
}