package com.example.peopledaliy.mvp.view.people.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.model.entity.UserFollowEntity
import com.example.peopledaliy.network.LoadImage

class UserFollowAdapter : BaseQuickAdapter<UserFollowEntity.Values, BaseViewHolder>(R.layout.item_follow_head) {
    override fun convert(baseViewHolder: BaseViewHolder, values: UserFollowEntity.Values) {
        baseViewHolder.setText(R.id.follow_item_tv, values.author_name)
        val imageView = baseViewHolder.getView<ImageView>(R.id.follow_item_img)
        values.author_img?.let { LoadImage().loadCircleImg(it, imageView) }
    }
}