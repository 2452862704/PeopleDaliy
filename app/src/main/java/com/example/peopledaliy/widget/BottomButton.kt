package com.example.peopledaliy.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils
import com.example.peopledaliy.R

class BottomButton: RelativeLayout,View.OnClickListener {
    var nomalImg=0//默认未选中图片id
    var selImg=0//选中图片id
    var nomalColor=0//默认文字颜色
    var selColor=0//选中文字颜色
    var content:String?=null//设置底部显示文字
    var showPoint=false//是否显示小红点标志
    var selFlag=false//当前控件是否被选中标志
    var listener:SelectListener?=null
    var contentTv:TextView? = null
    var img:ImageView? = null
    var numTv:TextView? = null
    var linearLayout:LinearLayout? = null

    fun setListener(listener:SelectListener):BottomButton{
        this.listener=listener
        return this
    }
    fun setContent(content:String):BottomButton{
        this.content=content
        if (this.content!!.isEmpty())
            return this
        contentTv!!.setText(content)
        return this
    }
    fun setNomalImg(nomalImg:Int):BottomButton{
        this.nomalImg=nomalImg
        if (!selFlag)
            img!!.setImageResource(this.nomalImg)
        return this
    }
    fun setSelImg(selImg:Int):BottomButton{
        this.selImg=selImg
        if (selFlag)
            img!!.setImageResource(this.selImg)
        return this
    }
    fun setNomalColor(nomalColor:Int):BottomButton{
        this.nomalColor=nomalColor
        if (!selFlag)
            contentTv!!.setTextColor(this.nomalColor)
        return this
    }
    fun setSelColor(selColor:Int):BottomButton{
        this.selColor=selColor
        if (selFlag)
            contentTv!!.setTextColor(this.selColor)
        return this
    }
    fun setShowPoint(showPoint:Boolean):BottomButton{
        this.showPoint=showPoint
        if (this.showPoint){
            numTv!!.visibility= VISIBLE
        }else{
            numTv!!.visibility= GONE
        }
        return this
    }
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        val typedArray = context!!.obtainStyledAttributes(attrs, R.styleable.bottom_arrs)
        selFlag = typedArray.getBoolean(R.styleable.bottom_arrs_selflag, false)
        typedArray.recycle()//释放TypedArray对象->内存泄漏
        init();
    }
    fun init(){
        linearLayout= LinearLayout(context);
        img= ImageView(context)
        contentTv = TextView(context)
        numTv = TextView(context)
        //先组合LinearLayout 下的ImageView 以及TextView
        linearLayout!!.orientation=LinearLayout.VERTICAL
        val imgLp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        img!!.layoutParams=imgLp
        val contentLp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        contentTv!!.layoutParams=contentLp
        linearLayout!!.gravity=Gravity.CENTER
        linearLayout!!.addView(img)
        linearLayout!!.addView(contentTv)
        val linearLp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        linearLp.addRule(RelativeLayout.CENTER_IN_PARENT)
        linearLayout!!.layoutParams=linearLp
        addView(linearLayout)
        val numLp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        numLp.rightMargin=ConvertUtils.dp2px(15f)
        numLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        numTv!!.layoutParams=numLp
        addView(numTv)
        setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        selFlag=true
        refreshSelect()
        if (listener!=null){
            listener!!.onSelect(id)
        }
    }
    fun clearSelect() {
        selFlag = false
        img!!.setImageResource(nomalImg)
        contentTv!!.setTextColor(nomalColor)
    }
    fun selectCheck() {
        selFlag = true
        img!!.setImageResource(selImg)
        contentTv!!.setTextColor(selImg)
    }
    fun refreshSelect() {
        if (selFlag) {
            img!!.setImageResource(selImg)
            contentTv!!.setTextColor(selColor)
            numTv!!.visibility = GONE
        }
    }
    //选中回调接口
    interface SelectListener{
        fun onSelect(id:Int)
    }
}