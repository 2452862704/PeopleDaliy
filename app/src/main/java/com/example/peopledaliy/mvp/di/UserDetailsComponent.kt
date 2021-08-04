package com.example.peopledaliy.mvp.di

import com.example.peopledaliy.mvp.view.user.HeadActivity
import dagger.Component

@Component(modules = arrayOf(UserDetailsModules::class))
interface UserDetailsComponent {
    fun inject(headActivity: HeadActivity)
}