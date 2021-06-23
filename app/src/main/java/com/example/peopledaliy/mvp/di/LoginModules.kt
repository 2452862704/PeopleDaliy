package com.example.peopledaliy.mvp.di

import com.example.peopledaliy.mvp.contract.LoginContract
import com.example.peopledaliy.mvp.model.LoginModel
import dagger.Module
import dagger.Provides

@Module
class LoginModules {
    var view:LoginContract.ILoginView?=null

    constructor(view: LoginContract.ILoginView?) {
        this.view = view
    }
    @Provides
    fun setView():LoginContract.ILoginView{
        return view!!
    }
    @Provides
    fun setModel():LoginContract.ILoginModel{
        return LoginModel()
    }
}