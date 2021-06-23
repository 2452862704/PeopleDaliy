package com.example.peopledaliy.mvp.di

import com.example.peopledaliy.MainActivity
import dagger.Component

@Component(modules = arrayOf(SplashModules::class))
interface SplashComponent {
    fun inject(mainActivity: MainActivity)
}