package com.example.peopledaliy.mvp.view.live
//adb shell monkey -p com.example.peopledaliy -v 500
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.peopledaliy.R
import com.tencent.live2.V2TXLivePlayer
import com.tencent.live2.impl.V2TXLivePlayerImpl
import com.tencent.rtmp.ui.TXCloudVideoView
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild.mContext


class LiveFragment : Fragment() {
    val flvUrl = "rtmp://58.200.131.2:1935/livetv/cctv15"
    lateinit var mLivePlayer: V2TXLivePlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_live, container, false)

        val mView = inflate!!.findViewById(R.id.video_view) as TXCloudVideoView
        mLivePlayer = V2TXLivePlayerImpl(context)
        mLivePlayer.setRenderView(mView)

        mLivePlayer.startPlay(flvUrl)

        return inflate
    }


    override fun onPause() {
        super.onPause()
        mLivePlayer.pauseVideo()
    }
}