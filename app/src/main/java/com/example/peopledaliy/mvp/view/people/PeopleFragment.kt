package com.example.peopledaliy.mvp.view.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.view.people.adapter.PeopleVpAdapter
import com.flyco.tablayout.SlidingTabLayout

class PeopleFragment : Fragment() {

    private var rootView: View? = null
    private var people_tab: SlidingTabLayout? = null
    private var people_vp: ViewPager? = null
    private var adapter: PeopleVpAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_people, container, false)
        init()
        return rootView
    }

    private fun init() {
        people_tab = rootView!!.findViewById(R.id.people_tab)
        people_vp = rootView!!.findViewById(R.id.people_vp)
        adapter = fragmentManager?.let { PeopleVpAdapter(it) }
        people_vp!!.setAdapter(adapter)
        people_tab!!.setViewPager(people_vp)
    }

    override fun onPause() {
        super.onPause()

    }
}