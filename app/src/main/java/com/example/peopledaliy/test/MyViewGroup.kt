package com.example.peopledaliy.test

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ListView

class MyViewGroup:ListView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
    }

    fun isBottom():Boolean{
        val firstVisiblePosition = firstVisiblePosition//屏幕上显示的第一条是list中的第几条
        val childcount = childCount //屏幕上显示多少条item
        val totalItemCount = count //一共有多少条
        if ((firstVisiblePosition+childcount)>=totalItemCount){
            return true
        }
        return false
    }
    fun isTop():Boolean{
        val firstVisiblePosition = firstVisiblePosition//屏幕上显示的第一条是list中的第几条
        if (firstVisiblePosition==0){
            return true
        }
        return false
    }
    var touch=0f
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        //重点在这里
        val action = ev!!.getAction()
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                super.onInterceptTouchEvent(ev);
                //不允许上层viewGroup拦截事件.
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            MotionEvent.ACTION_MOVE -> {
                //满足listView滑动到顶部，如果继续下滑，那就让scrollView拦截事件
                if (getFirstVisiblePosition() == 0 && (ev.getY() - touch) > 0) {
                    //允许上层viewGroup拦截事件
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                //满足listView滑动到底部，如果继续上滑，那就让scrollView拦截事件
                //getLastVisiblePosition list集合的总大小
                else if (getLastVisiblePosition() == getCount() - 1 && (ev.getY() - touch) < 0) {
                    //允许上层viewGroup拦截事件
                    Log.e("123", "dispatchTouchEvent: "+getCount(), )
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    //不允许上层viewGroup拦截事件
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
            }
            MotionEvent.ACTION_UP -> {
                //不允许上层viewGroup拦截事件
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        touch = ev!!.getY();
        return super.dispatchTouchEvent(ev)
    }
}