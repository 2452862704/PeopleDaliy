package com.example.peopledaliy.mvp.presenter

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.presenter.BasePresenter
import com.example.peopledaliy.mvp.contract.FrameContract
import javax.inject.Inject

class FramePresenter @Inject constructor(m:FrameContract.IFrameModel, v:FrameContract.IFrameView):BasePresenter<FrameContract.IFrameModel,FrameContract.IFrameView>(m, v) {
    override fun onNext(t: BaseEntity) {

    }
}