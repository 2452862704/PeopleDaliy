package com.example.peopledaliy.mvp.model.entity

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import java.util.*

class ChannelEntity : BaseEntity() {
    var values: String? = null
    fun getValues(): List<Values>? {
        if (values == null) return null
        val list: MutableList<Values> = ArrayList()
        try {
            val jsonArray = JSONArray(values)
            for (i in 0 until jsonArray.length()) {
                val str = jsonArray.getString(i)
                val entity = Gson().fromJson(
                    str,
                    Values::class.java
                )
                list.add(entity)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    class Values {
        var channel_name: String? = null
        var channel_id: Long = 0
        var channel_hot = 0
        var isDragEnable = true
        var isDropEnable = true
    }
}