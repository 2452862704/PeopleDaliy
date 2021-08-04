package com.example.peopledaliy.mvp.model.entity

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException
import java.util.*

class NewsEntity : BaseEntity() {
    var values: String? = null
    fun getValues(): List<Values>? {
        if (values == null) return null
        if (values!!.isEmpty()) return null
        val list: MutableList<Values> = ArrayList()
        try {
            val jsonArray = JSONArray(values)
            for (i in 0 until jsonArray.length()) {
                val str = jsonArray.getString(i)
                val entity = Gson().fromJson(str, Values::class.java)
                list.add(entity)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    class Values : MultiItemEntity {
        var news_type = 0
        var news_author: String? = null
        var news_value: String? = null
        var news_title: String? = null
        var news_url: String? = null
        var news_time: Long = 0
        var author_id: Long = 0
        var channel_id: Long = 0
        var news_id: Long = 0

        //            if (medias.isEmpty())
//                return null;
//            List<MediaValues>medialist = new ArrayList<>();
//            try {
//                JSONArray jsonArray = new JSONArray(medias);
//                for (int i = 0;i < jsonArray.length();i ++){
//                    String str = jsonArray.getString(i);
//                    MediaValues mediaValues = new Gson().fromJson(str,MediaValues.class);
//                    medialist.add(mediaValues);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        var medias: List<MediaValues>? = null



        override val itemType: Int
            get() = gettype()
        fun gettype(): Int {
            if (medias == null) return NONECODE
            if (medias!!.size == 0) return NONECODE
            if (medias!!.size == 1) return IMG1CODE
            if (medias!!.size == 2) return IMG2CODE
            return if (medias!!.size == 3) IMG3CODE else NONECODE
        }
    }

    class MediaValues {
        var media_type = 0
        var media_id: Long = 0
        var media_url: String? = null
    }

    companion object {
        const val NONECODE = 0
        const val IMG1CODE = 1
        const val IMG2CODE = 2
        const val IMG3CODE = 3
    }
}
