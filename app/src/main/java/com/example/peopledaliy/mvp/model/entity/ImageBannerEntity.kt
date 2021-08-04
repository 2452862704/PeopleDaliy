package com.example.peopledaliy.mvp.model.entity

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import java.util.*

class ImageBannerEntity : BaseEntity() {
    var values: String? = null
    fun getValues(): List<NewsEntity.Values>? {
        if (values!!.isEmpty()) return null
        val list: MutableList<NewsEntity.Values> = ArrayList()
        try {
            val jsonArray = JSONArray(values)
            for (i in 0 until jsonArray.length()) {
                val str = jsonArray.getString(i)
                val entity = Gson().fromJson(str, NewsEntity.Values::class.java)
                list.add(entity)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}
