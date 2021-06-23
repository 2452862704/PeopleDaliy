package com.example.peopledaliy.widget


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import com.blankj.utilcode.util.ConvertUtils
import com.example.peopledaliy.R


class LoadDialog constructor(context: Context):Dialog(context) {
    private var rotateAnimation: RotateAnimation? = null
    private var rootView: RelativeLayout? = null
    private var contentView: RelativeLayout? = null
    private var img: ImageView? = null
init {
    init()
}
     fun init() {
        rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.load_anim) as RotateAnimation
        rotateAnimation!!.reset()
        rootView = RelativeLayout(context)
        val lp = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        rootView!!.layoutParams = lp
        contentView = RelativeLayout(context)
        val contentLp = RelativeLayout.LayoutParams(
            ConvertUtils.dp2px(200f),
            ConvertUtils.dp2px(100f)
        )
        contentLp.addRule(RelativeLayout.CENTER_VERTICAL)
        contentView!!.layoutParams = contentLp
        //        contentView.setBackgroundResource(R.drawable.dialog_bg);
        rootView!!.addView(contentView)
        img = ImageView(context)
        val imgLp = RelativeLayout.LayoutParams(
            ConvertUtils.dp2px(80f),
            ConvertUtils.dp2px(80f)
        )
        imgLp.addRule(RelativeLayout.CENTER_IN_PARENT)
        img!!.setLayoutParams(imgLp)
        contentView!!.addView(img)
        img!!.setImageResource(R.drawable.res_icon_240)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(rootView!!)
    }

    override fun show() {
        super.show()
        img!!.startAnimation(rotateAnimation)
    }

    override fun dismiss() {
        super.dismiss()
        rotateAnimation!!.cancel()
        img!!.clearAnimation()
    }
}