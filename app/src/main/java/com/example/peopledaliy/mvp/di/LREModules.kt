package com.example.peopledaliy.mvp.di

import com.example.peopledaliy.mvp.contract.LREContract
import com.example.peopledaliy.mvp.model.LREModel
import dagger.Module
import dagger.Provides

@Module
class LREModules {
    var ilreView: LREContract.ILREView? = null

    constructor(ilreView: LREContract.ILREView?) {
        this.ilreView = ilreView
    }

    @Provides
    fun providerView(): LREContract.ILREView {
        return ilreView!!
    }

    @Provides
    fun providerModel(): LREContract.ILREModel {
        return LREModel()
    }
}