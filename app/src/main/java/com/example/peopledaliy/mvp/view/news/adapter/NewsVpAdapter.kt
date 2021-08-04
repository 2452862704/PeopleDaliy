package com.example.peopledaliy.mvp.view.news.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.peopledaliy.mvp.model.entity.ChannelEntity

class NewsVpAdapter(
    fm: FragmentManager,
    private val fragments: List<Fragment>,
    list: List<ChannelEntity.Values>
) :
    FragmentPagerAdapter(fm) {
    private val list: List<ChannelEntity.Values>
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "" + list[position].channel_name
    }

    init {
        this.list = list
    }
}