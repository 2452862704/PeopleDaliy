package com.example.peopledaliy.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.blankj.utilcode.util.ConvertUtils
import com.example.peopledaliy.R

class TimerLinView:View {
    var selBit: Bitmap?=null
    var popBit: Bitmap?=null
    var selColor: Int?=null
    var nomalColor: Int?=null
    var popHeight: Int?=null
    var width: Int?=null
    var height: Int?=null
    var itemWidth: Int?=null
    val tvHeight = ConvertUtils.dp2px(20f) //签到文字高度
    var selIndex=0
    val names = arrayOf("1连签", "2连签", "3连签", "4连签", "5连签", "6连签", "7连签")
    val numes = arrayOf("5", "10", "10", "20", "25", "25", "50")
    var paint= Paint(Paint.ANTI_ALIAS_FLAG)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width = measuredWidth
        height=measuredHeight
        itemWidth = width!! / 13
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawSign(canvas!!)
    }
    fun drawSign(canvas: Canvas){
        for (i in 0..12){
            if (i%2==0){
                //偶数画圈
                paint.color=nomalColor!!
                if (i<=selIndex*2){
                    paint.color=selColor!!
                }
                if (i==6||i==12){
                    paint.color=selColor!!
                }
                canvas.drawCircle((itemWidth!! * i + itemWidth!! / 2).toFloat(), (popHeight!! + (height!! - popHeight!! - tvHeight) / 2).toFloat(), (itemWidth!! / 2).toFloat(), paint)
                //绘制圆圈中间的签到积分
                paint.color=Color.WHITE
                canvas.drawCircle((itemWidth!!*i+itemWidth!!/2).toFloat(), (popHeight!!+(height!!-popHeight!!-tvHeight)/2).toFloat(), (itemWidth!!/2-ConvertUtils.dp2px(1f)).toFloat(),paint)
                paint.color=nomalColor!!
                if (i==6||i==12){
                    paint.color=selColor!!
                }
                paint.textSize= (itemWidth!!/2).toFloat()
                paint.isFakeBoldText=true
                paint.textAlign=Paint.Align.CENTER
                canvas.drawText(numes[i/2],itemWidth!!*i+itemWidth!!/2f,(popHeight!!+(height!!-popHeight!!-tvHeight)/2+(itemWidth!!/4).toFloat()),paint)
                paint.color=nomalColor!!
                //绘制气泡弹窗
                if (i==6||i==12){
                    canvas.drawBitmap(popBit!!,(itemWidth!!*i).toFloat(),0f,paint)
                    paint.textSize= (itemWidth!!/4).toFloat()
                    paint.color=Color.WHITE
                    canvas.drawText("翻倍",
                        (itemWidth!!*i+itemWidth!!/2).toFloat(),
                        (popHeight!!/2+ConvertUtils.dp2px(1f)).toFloat(),paint)
                    paint.color=selColor!!
                }
                paint.textSize= (itemWidth!!/3).toFloat()
                canvas.drawText(names[i/2],
                    (itemWidth!!*i+itemWidth!!/2).toFloat(),
                    (height!!-ConvertUtils.dp2px(2f)).toFloat(),paint)
                //绘制用户已签到图片
                if (i<=selIndex*2){
                    val rect = Rect()
                    rect.left= itemWidth!! * i + ConvertUtils.dp2px(1f)
                    rect.right=itemWidth!!*(i+1)-ConvertUtils.dp2px(1f)
                    rect.top=popHeight!!+(height!!-popHeight!!-tvHeight)/2-itemWidth!!/2+ConvertUtils.dp2px(1f)
                    rect.bottom=popHeight!!+(height!!-popHeight!!-tvHeight)/2+itemWidth!!/2-ConvertUtils.dp2px(1f)
                    canvas.drawBitmap(selBit!!,null,rect,paint)
                }
            }else{
                paint.color=nomalColor!!
                canvas.drawLine(
                    (itemWidth!!*i).toFloat(),
                    (popHeight!!+(height!!-popHeight!!-tvHeight!!)/2-ConvertUtils.dp2px(0f)/2).toFloat(),
                    (itemWidth!!*(i+1)).toFloat(),
                    (popHeight!!+(height!!-popHeight!!-tvHeight)/2+ConvertUtils.dp2px(0f)/2).toFloat(),paint)
            }
        }
    }
    fun init(){
        selBit = BitmapFactory.decodeResource(resources, R.mipmap.res_7icon_362)
        popBit = BitmapFactory.decodeResource(resources, R.mipmap.module_integral_double_bg)
        selColor = Color.RED
        nomalColor = Color.GRAY
        popHeight = popBit!!.height + ConvertUtils.dp2px(5f)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (selBit!=null){
            selBit!!.recycle()
        }
        if (popBit!=null){
            popBit!!.recycle()
        }
    }
}