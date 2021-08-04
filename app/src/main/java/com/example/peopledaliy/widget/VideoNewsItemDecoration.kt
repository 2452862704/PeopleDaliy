package com.example.peopledaliy.widget

import android.content.Context
import com.yanyusong.y_divideritemdecoration.Y_Divider
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration

class VideoNewsItemDecoration(context: Context?) :
    Y_DividerItemDecoration(context) {
    override fun getDivider(itemPosition: Int): Y_Divider? {
        var divider: Y_Divider? = null
        divider = if (itemPosition % 2 == 0) {
            //左侧列->底部以及右侧
            Y_DividerBuilder()
                .setRightSideLine(true, -0x1, 1f, 0f, 0f)
                .setBottomSideLine(true, -0x1, 1f, 0f, 0f).create()
        } else {
            //右侧列->底部
            Y_DividerBuilder()
                .setBottomSideLine(true, -0x1, 1f, 0f, 0f).create()
        }
        return divider
    }
}