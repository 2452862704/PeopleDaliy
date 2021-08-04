package com.example.peopledaliy.mvp.model.entity

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import java.util.*

class UserFollowEntity : BaseEntity() {
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
        var author_name: String? = null
        var author_attestation: String? = null
        var author_value: String? = null
        var author_img: String? = null
        var author_id: Long = 0
        var channel_id: Long = 0
    }
}
