package com.example.peopledaliy

import android.app.Application

class App:Application() {
    companion object{
        private var instance: App? = null
        fun getInstance(): App? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDb()
    }
    fun initDb(){
    }

}