package com.example.peopledaliy.mvp.view.user.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.peopledaliy.mvp.model.entity.UserItemEntity
import com.example.peopledaliy.R

class UserAdapter : BaseQuickAdapter<UserItemEntity, BaseViewHolder>(R.layout.item_user_itemes) {
    override fun convert(baseViewHolder: BaseViewHolder, userItemEntity: UserItemEntity) {
        baseViewHolder.setText(R.id.user_itemes_tv, userItemEntity.name)
        baseViewHolder.setImageResource(R.id.user_itemes_arrow_img, R.mipmap.icon_pn_arrows)
        if (userItemEntity.name.equals("夜间模式")) {
            if (userItemEntity.selFlag) {
                baseViewHolder.setImageResource(R.id.user_itemes_arrow_img, R.mipmap.new_on)
            } else {
                baseViewHolder.setImageResource(R.id.user_itemes_arrow_img, R.mipmap.new_off)
            }
        }
    }

    init {
        addChildClickViewIds(R.id.user_itemes_arrow_img)
    }
}