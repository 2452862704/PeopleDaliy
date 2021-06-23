package com.example.peopledaliy.mvp.view.user.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.peopledaliy.mvp.model.entity.UserMenuEntity
import com.example.peopledaliy.R

class UserMenuAdapter:BaseQuickAdapter<UserMenuEntity,BaseViewHolder>(R.layout.item_user_menu) {
    override fun convert(holder: BaseViewHolder, item: UserMenuEntity) {
        holder.setText(R.id.user_menu_item_name,item.name)
        holder.setImageResource(R.id.user_menu_icon,item.imgId)
    }
}