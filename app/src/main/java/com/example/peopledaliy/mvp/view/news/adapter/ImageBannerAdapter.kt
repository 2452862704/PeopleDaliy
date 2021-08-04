package com.example.peopledaliy.mvp.view.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.model.entity.NewsEntity
import com.example.peopledaliy.network.LoadImage

class ImageBannerAdapter(list: List<NewsEntity.Values>) :
    PagerAdapter() {
    private val list: List<NewsEntity.Values>
    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(container.context)
            .inflate(R.layout.item_news_banner, container, false)
        container.addView(view)
        val url: String? = list[position].medias!!.get(0).media_url
        val img = view.findViewById<ImageView>(R.id.news_banner_img)
        LoadImage().loadMatchImg(url!!, img)
        return view
    }

    init {
        this.list = list
    }
}
