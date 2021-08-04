package com.example.peopledaliy.mvp.model.entity

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import java.util.*

class VideoNewsEntity : BaseEntity() {
    var values: String? = null
    fun getValues(): List<Values>? {
        val list: MutableList<Values> = ArrayList()
        if (values == null) return null
        try {
            val jsonArray = JSONArray(values)
            for (i in 0 until jsonArray.length()) {
                val str = jsonArray.getString(i)
                val value = Gson().fromJson(
                    str,
                    Values::class.java
                )
                list.add(value)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    class Values {
        var news_type = 0
        var news_author: String? = null
        var news_value: String? = null
        var news_title: String? = null
        var news_url: String? = null
        var news_time: Long = 0
        var author_id: Long = 0
        var channel_id: Long = 0
        var news_id: Long = 0
        var medias: List<MediaValues>? = null
    }

    class MediaValues {
        var media_type = 0
        var media_id: Long = 0
        var media_url: String? = null
    }
}
