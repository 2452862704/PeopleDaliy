package com.example.peopledaliy.widget

import android.content.Context
import com.yanyusong.y_divideritemdecoration.Y_Divider
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration

class LinearDividerItemDecoration:Y_DividerItemDecoration {
    var size = 0
    constructor(context: Context?) : super(context){

    }

    override fun getDivider(itemPosition: Int): Y_Divider {
        var divider: Y_Divider? = null
        divider = if (itemPosition == 4) {
            Y_DividerBuilder().setBottomSideLine(true, -0x1000000, 0f, 0f, 0f).create()
        } else if (itemPosition == 0) {
            Y_DividerBuilder().setBottomSideLine(true, -0x1000000, 0f, 0f, 0f).create()
        } else Y_DividerBuilder().setBottomSideLine(true, -0x3e3e3f, 1f, 0f, 0f).create()
        return divider
    }
}