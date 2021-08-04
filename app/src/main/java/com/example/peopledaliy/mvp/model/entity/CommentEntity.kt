package com.example.peopledaliy.mvp.model.entity

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import java.util.*

class CommentEntity : BaseEntity() {
    var values: String? = null
    fun getValues(): List<Values>? {
        val list: MutableList<Values> = ArrayList()
        if (values == null) return null
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
        var comment_address: String? = null
        var comment_time: Long = 0
        var comment_fabulous = 0
        var user_id = 0
        var comment_value: String? = null
        var comment_id: Long = 0
        var news_id: Long = 0
    }
}
