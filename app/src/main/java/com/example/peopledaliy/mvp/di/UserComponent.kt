package com.example.peopledaliy.mvp.di

import com.example.peopledaliy.mvp.view.user.UserFragment
import dagger.Component

@Component(modules = arrayOf(UserModules::class))
interface UserComponent {
    fun inject(userFragment: UserFragment)
}