package com.example.peopledaliy.mvp.view.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.view.video.adapter.VideoVpAdapter
import com.flyco.tablayout.SlidingTabLayout


class VideoFragment : Fragment() {
    private var rootView: View? = null
    private var video_tab: SlidingTabLayout? = null
    private var video_vp: ViewPager? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_video, container, false)
        init()
        return rootView
    }

    private fun init() {
        video_tab = rootView!!.findViewById(R.id.video_tab)
        video_vp = rootView!!.findViewById(R.id.video_vp)
        video_vp!!.setAdapter(fragmentManager?.let { VideoVpAdapter(it) })
        video_tab!!.setViewPager(video_vp)
    }
}