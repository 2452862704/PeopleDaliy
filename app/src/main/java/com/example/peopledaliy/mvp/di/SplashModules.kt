package com.example.peopledaliy.mvp.di

import com.example.peopledaliy.mvp.contract.SplashContract
import com.example.peopledaliy.mvp.model.SplashModel
import dagger.Module
import dagger.Provides

@Module
class SplashModules {
    var view:SplashContract.ISplashView?=null

    constructor(vie: SplashContract.ISplashView?) {
        this.view = vie
    }
    @Provides
    fun setView():SplashContract.ISplashView{
        return view!!
    }
    @Provides
    fun setModel():SplashContract.ISplashModel{
        return SplashModel()
    }
}