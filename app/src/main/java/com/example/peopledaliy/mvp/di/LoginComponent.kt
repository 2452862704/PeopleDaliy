package com.example.peopledaliy.mvp.di

import com.example.peopledaliy.mvp.view.user.LoginFragment
import com.example.peopledaliy.mvp.view.user.RegisterFragment
import dagger.Component

@Component(modules = arrayOf(LoginModules::class))
interface LoginComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(registerFragment: RegisterFragment)
}