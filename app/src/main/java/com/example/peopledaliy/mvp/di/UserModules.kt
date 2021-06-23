package com.example.peopledaliy.mvp.di

import com.example.peopledaliy.mvp.contract.UserContract
import com.example.peopledaliy.mvp.model.UserModel
import dagger.Module
import dagger.Provides

@Module
class UserModules {
    var view:UserContract.IUserView?=null

    constructor(view: UserContract.IUserView?) {
        this.view = view
    }
    @Provides
    fun setView():UserContract.IUserView{
        return view!!
    }
    @Provides
    fun setModel():UserContract.IUserModel{
        return UserModel()
    }
}