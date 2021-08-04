package com.example.peopledaliy.mvp.di

import com.example.peopledaliy.mvp.contract.UserDetailsContract
import com.example.peopledaliy.mvp.model.UserDetailsModel
import dagger.Module
import dagger.Provides

@Module
class UserDetailsModules {
     var view: UserDetailsContract.IUserDetailsView? = null

    constructor(view: UserDetailsContract.IUserDetailsView?) {
        this.view = view
    }
    @Provides
    fun setView():UserDetailsContract.IUserDetailsView{
        return view!!
    }
    @Provides
    fun setModel():UserDetailsContract.IUserDetailsModel{
        return UserDetailsModel()
    }
}