package com.example.peopledaliy.mvp.view.news.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.model.entity.CommentEntity
import com.tb.emoji.EmojiUtil
import java.io.IOException

class CommentAdapter :
    BaseQuickAdapter<CommentEntity.Values, BaseViewHolder>(R.layout.item_comment) {

    override fun convert(holder: BaseViewHolder, item: CommentEntity.Values) {
        holder!!.setText(R.id.comment_item_fabulous_tv, "" + item.comment_fabulous)
        holder.setText(R.id.comment_item_name_tv, "" + item.user_id)
        holder.setText(R.id.comment_item_time_tv, "" + item.comment_time)
        holder.setText(R.id.comment_item_address_tv, item.comment_address)
        val comment_item_values_tv = holder.getView<TextView>(R.id.comment_item_values_tv)
        try {
            EmojiUtil.handlerEmojiText(comment_item_values_tv, item.comment_value, context)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}