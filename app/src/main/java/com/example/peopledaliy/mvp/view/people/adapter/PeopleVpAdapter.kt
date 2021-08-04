package com.example.peopledaliy.mvp.view.people.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.peopledaliy.mvp.view.people.RecommendFragment
import java.util.*

class PeopleVpAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    private val list: MutableList<Fragment> = ArrayList()
    private val titles = arrayOf("推荐", "关注")
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
        list.add(RecommendFragment())
        list.add(FollowFragment())
    }
}
