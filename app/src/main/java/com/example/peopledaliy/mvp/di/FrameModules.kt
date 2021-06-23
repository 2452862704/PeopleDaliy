package com.example.peopledaliy.mvp.di

import com.example.peopledaliy.mvp.contract.FrameContract
import com.example.peopledaliy.mvp.model.FrameModel
import dagger.Module
import dagger.Provides

@Module
class FrameModules {
    var view:FrameContract.IFrameView?=null

    constructor(view: FrameContract.IFrameView?) {
        this.view = view
    }
    @Provides
    fun setView():FrameContract.IFrameView{
        return view!!
    }
    @Provides
    fun setModel():FrameContract.IFrameModel{
        return FrameModel()
    }
}