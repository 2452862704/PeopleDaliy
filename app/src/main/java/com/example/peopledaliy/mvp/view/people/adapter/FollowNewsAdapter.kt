package com.example.peopledaliy.mvp.view.people.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.model.entity.FollowEntity
import com.example.peopledaliy.network.LoadImage

class FollowNewsAdapter : BaseQuickAdapter<FollowEntity.Values, BaseViewHolder>(R.layout.item_follow) {
    override fun convert(baseViewHolder: BaseViewHolder, values: FollowEntity.Values) {
        baseViewHolder.setText(R.id.follow_item_name_tv, values.author_name)
        baseViewHolder.setText(R.id.follow_item_value_tv, values.author_attestation)
        baseViewHolder.setText(R.id.follow_item_title, values.news_title)
        baseViewHolder.setText(R.id.follow_item_time, "" + values.news_time)
        val headImg = baseViewHolder.getView<ImageView>(R.id.follow_item_head_img)
        val img = baseViewHolder.getView<ImageView>(R.id.follow_item_img)
        values.author_img?.let { LoadImage().loadCircleImg(it, headImg) }
        values.medias!!.get(0).media_url?.let { LoadImage().loadMatchImg(it, img) }
    }
}