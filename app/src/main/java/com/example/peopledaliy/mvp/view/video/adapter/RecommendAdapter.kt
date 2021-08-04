package com.example.peoplenews.mvp.view.people.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.model.entity.RecommendEntity
import com.example.peopledaliy.mvp.model.entity.TestVideoUrl
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer


class RecommendAdapter : RecyclerView.Adapter<RecommendAdapter.VideoHolder> {

    var arrayList = ArrayList<RecommendEntity.Values>()
    lateinit var mGSYVideo: StandardGSYVideoPlayer

    constructor(arrayList: ArrayList<RecommendEntity.Values>) : super() {
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_video_recommend, null)
        return VideoHolder(inflate)
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        mGSYVideo = holder.mGSYVideo!!
        holder.mGSYVideo!!.setUp(TestVideoUrl.getPlayUrl(position), true, "")
        holder.mGSYVideo!!.startPlayLogic()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun pause(){
        mGSYVideo.onVideoPause()
    }
    class VideoHolder : RecyclerView.ViewHolder {
        var mGSYVideo: StandardGSYVideoPlayer? = null
        constructor(mView: View) : super(mView) {
            mGSYVideo = mView.findViewById(R.id.StandardGSYVideoPlayer)
        }
    }
}