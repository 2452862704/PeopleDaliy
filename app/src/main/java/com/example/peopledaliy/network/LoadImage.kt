package com.example.peopledaliy.network

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.peopledaliy.R
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class LoadImage {
    fun loadMatchImg(url:String,img:ImageView){
        Glide.with(img.context).load(Api.FileUrl+url)
            .placeholder(R.mipmap.cutdown_dialog_bg)
            .error(R.mipmap.tt_default_image_error)
            .centerCrop()
            .into(img)
    }
    fun loadCircleImg(url: String,img: ImageView){
        Glide.with(img.context)
            .load(Api.FileUrl+url)
            .error(R.mipmap.tt_default_image_error)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(img)
    }
    fun loadRoundImg(url: String,img: ImageView,radius:Int){
        Glide.with(img.context)
            .load(Api.FileUrl+url)
            .transform(MultiTransformation(RoundedCornersTransformation(radius, 0)))
            .into(img)
    }
}