package com.example.peopledaliy.mvp.view.video.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.peopledaliy.mvp.view.people.RecommendFragment
import com.example.peopledaliy.mvp.view.video.SmallVideoFragment
import java.util.*

class VideoVpAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    private val list: MutableList<Fragment>
    private val titles = arrayOf("推荐", "小视频")
    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    init {
        list = ArrayList()
        list.add(RecommendFragment())
        list.add(SmallVideoFragment())
    }
}