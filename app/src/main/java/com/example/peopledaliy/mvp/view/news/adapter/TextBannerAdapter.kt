package com.example.peopledaliy.mvp.view.news.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.model.entity.TextBannerEntity
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.util.BannerUtils

class TextBannerAdapter(datas: List<TextBannerEntity.Values?>?) :
    BannerAdapter<TextBannerEntity.Values?, TextBannerAdapter.TextBannerHolder?>(datas) {
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): TextBannerHolder {
        return TextBannerHolder(BannerUtils.getView(parent, R.layout.item_news_text))
    }

    override fun onBindView(
        holder: TextBannerHolder?,
        data: TextBannerEntity.Values?,
        position: Int,
        size: Int
    ) {
        holder!!.title.text = "" + data!!.news_title
    }

    inner class TextBannerHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var title: TextView

        init {
            title = view.findViewById(R.id.news_text_title)
        }
    }
}