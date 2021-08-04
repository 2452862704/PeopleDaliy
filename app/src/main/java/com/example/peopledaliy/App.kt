package com.example.peopledaliy

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import com.example.peopledaliy.db.DaoMaster
import com.example.peopledaliy.db.DaoSession
import com.squareup.leakcanary.LeakCanary
import com.tencent.rtmp.TXLiveBase




class App:Application() {
    var mSession: DaoSession? = null
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
        // 1、获取需要连接的数据库

        // 1、获取需要连接的数据库
        val devOpenHelper = DaoMaster.DevOpenHelper(this, "paper.db")
        val db: SQLiteDatabase = devOpenHelper.getWritableDatabase()
        // 2、创建数据库连接
        // 2、创建数据库连接
        val daoMaster = DaoMaster(db)
        // 3、创建数据库会话
        // 3、创建数据库会话
        mSession = daoMaster.newSession()
    }

    // 供外接使用
    fun getDaoSession(): DaoSession? {
        return mSession
    }

}