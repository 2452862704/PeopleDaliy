package com.example.peopledaliy.widget

import android.app.Activity
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import com.example.peopledaliy.R

class SexPop(private val context: Activity, private val callBack: SexCallBack) :
    View.OnClickListener {
    private val view: View
    private val camera_btn: Button
    private val photo_btn: Button
    private val canel_btn: Button
    private val popupWindow: PopupWindow
    fun show(rootView: View?) {
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0)
    }

    override fun onClick(v: View) {
        val id = v.id
        val intent = Intent(context, PhotoActivity::class.java)
        if (R.id.create_live_btn === id) {
            //开启相机
            callBack.sexCall(0)
        } else if (R.id.create_video_btn === id) {
            //开启相册
            callBack.sexCall(1)
        }
        popupWindow.dismiss()
    }

    interface SexCallBack {
        fun sexCall(sexCode: Int)
    }

    init {
        view = LayoutInflater.from(context).inflate(R.layout.pop_user, null)
        popupWindow = PopupWindow(
            view,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        camera_btn = view.findViewById(R.id.create_live_btn)
        camera_btn.text = "男"
        camera_btn.setOnClickListener(this)
        photo_btn = view.findViewById(R.id.create_video_btn)
        photo_btn.text = "女"
        photo_btn.setOnClickListener(this)
        canel_btn = view.findViewById(R.id.create_canel_btn)
        canel_btn.setOnClickListener(this)
    }
}