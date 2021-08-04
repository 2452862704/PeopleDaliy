package com.example.peopledaliy.mvp.view.news.adapter

import android.widget.ImageView
import com.blankj.utilcode.util.TimeUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.model.entity.NewsEntity
import com.example.peopledaliy.network.LoadImage
import java.util.*

class ContentNewsAdapter :
    BaseMultiItemQuickAdapter<NewsEntity.Values, BaseViewHolder>() {
    fun ContentNewsAdapter() {
        //3.+版本时 addChildClickViewIds()
        addItemType(NewsEntity.NONECODE, R.layout.item_news4)
        addItemType(NewsEntity.IMG1CODE, R.layout.item_news1)
        addItemType(NewsEntity.IMG2CODE, R.layout.item_news3)
        addItemType(NewsEntity.IMG3CODE, R.layout.item_news2)
    }

    init {
        //3.+版本时 addChildClickViewIds()
        addItemType(NewsEntity.NONECODE, R.layout.item_news4)
        addItemType(NewsEntity.IMG1CODE, R.layout.item_news1)
        addItemType(NewsEntity.IMG2CODE, R.layout.item_news3)
        addItemType(NewsEntity.IMG3CODE, R.layout.item_news2)
    }

    override fun convert(holder: BaseViewHolder, item: NewsEntity.Values) {
        //由于Android时间戳System.currentTimeMillis()获取后单位为毫秒值
        //上传服务器时值/1000,服务器获取的时间戳使用时*1000
        val date = Date(item!!.news_time * 1000)
        val timeStr = TimeUtils.getFriendlyTimeSpanByNow(date)
        if (item!!.itemType === NewsEntity.NONECODE) {
            holder!!.setText(R.id.news4_title, item!!.news_title)
            holder!!.setText(R.id.news4_author, item!!.news_author)
            if (item!!.news_time <= 0) {
                holder!!.setText(R.id.news4_time, "很久以前")
                return
            }
            holder!!.setText(R.id.news4_time, timeStr)
            return
        }
        if (item!!.itemType === NewsEntity.IMG1CODE) {
            holder!!.setText(R.id.news1_title, item!!.news_title)
            holder!!.setText(R.id.news1_author, item.news_author)
            if (item!!.news_time <= 0) {
                holder.setText(R.id.news1_time, "很久以前")
                return
            }
            holder.setText(R.id.news1_time, timeStr)
            val img = holder.getView<ImageView>(R.id.news1_img)
            item!!.medias!!.get(0).media_url?.let { LoadImage().loadMatchImg(it, img) }
            return
        }
        if (item!!.itemType === NewsEntity.IMG2CODE) {
            holder!!.setText(R.id.news3_title, item!!.news_title)
            holder!!.setText(R.id.news3_author, item!!.news_author)
            if (item!!.news_time <= 0) {
                holder.setText(R.id.news3_time, "很久以前")
                return
            }
            holder!!.setText(R.id.news3_time, timeStr)
            val img = holder.getView<ImageView>(R.id.news3_img1)
            val img1 = holder.getView<ImageView>(R.id.news3_img2)
            item!!.medias!!.get(0).media_url?.let { LoadImage().loadMatchImg(it, img) }
            item!!.medias!!.get(1).media_url?.let { LoadImage().loadMatchImg(it, img1) }
            return
        }
        if (item!!.itemType === NewsEntity.IMG3CODE) {
            holder!!.setText(R.id.news2_title, item.news_title)
            holder!!.setText(R.id.news2_author, item.news_author)
            if (item!!.news_time <= 0) {
                holder!!.setText(R.id.news2_time, "很久以前")
                return
            }
            holder!!.setText(R.id.news2_time, timeStr)
            val img = holder.getView<ImageView>(R.id.news2_img1)
            val img1 = holder.getView<ImageView>(R.id.news2_img2)
            val img2 = holder.getView<ImageView>(R.id.news2_img3)
            item!!.medias!!.get(0).media_url?.let { LoadImage().loadMatchImg(it, img) }
            item!!.medias!!.get(1).media_url?.let { LoadImage().loadMatchImg(it, img1) }
            item!!.medias!!.get(2).media_url?.let { LoadImage().loadMatchImg(it, img2) }
            return
        }
    }
}
