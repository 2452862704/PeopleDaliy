package com.example.peopledaliy.mvp.model.entity

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import java.util.*
class TextBannerEntity : BaseEntity() {
    var values: String? = null
    fun getValues(): List<Values>? {
        if (values!!.isEmpty()) return null
        val list: MutableList<Values> = ArrayList()
            //new Gson().fromJson(values,Values.class)
            //[{}.{}]
            try {
                val jsonArray = JSONArray(values)
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getString(i)
                    val entity = Gson().fromJson(
                        item,
                        Values::class.java
                    )
                    list.add(entity)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            //为测试效果将list中数据进行复用
            for (i in 1..4) {
                val entity = list[0]
                list.add(entity)
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
        }
    }