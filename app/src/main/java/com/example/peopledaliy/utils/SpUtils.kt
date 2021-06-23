package com.example.peopledaliy.utils

import android.content.SharedPreferences
import com.example.peopledaliy.App


class SpUtils {
    private val name = "netcatch"

    fun saveData(key: String?, value: String?) {
        val sp: SharedPreferences =
            App.getInstance()!!.getApplicationContext().getSharedPreferences(name, 0)
        sp.edit().putString(key, value).commit()
    }

    fun readData(key: String?): String? {
        val sp: SharedPreferences =
            App.getInstance()!!.getApplicationContext().getSharedPreferences(name, 0)
        return sp.getString(key, "")
    }

}