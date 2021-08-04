package com.example.peopledaliy.mvp.view.video.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.model.entity.VideoNewsEntity
import com.example.peopledaliy.network.LoadImage

class SmallVideoAdapter : BaseQuickAdapter<VideoNewsEntity.Values, BaseViewHolder>(R.layout.item_small_video) {
    override fun convert(baseViewHolder: BaseViewHolder, values: VideoNewsEntity.Values) {
        baseViewHolder.setText(R.id.small_item_title_tv, values.news_title)
        val img = baseViewHolder.getView<ImageView>(R.id.small_item_cover_img)
        values.medias!!.get(0).media_url?.let { LoadImage().loadMatchImg(it, img) }
    }
}