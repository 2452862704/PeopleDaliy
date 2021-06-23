package com.example.peopledaliy.mvp.di

import com.example.peopledaliy.mvp.view.FrameActivity
import dagger.Component

@Component(modules = arrayOf(FrameModules::class))
interface FrameComponent {
    fun inject(frameActivity: FrameActivity)
}