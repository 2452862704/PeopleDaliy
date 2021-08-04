package com.example.peopledaliy.mvp.di

import com.example.peopledaliy.mvp.view.live.LiveFragment
import com.example.peopledaliy.mvp.view.news.ContentNewsFragment
import com.example.peopledaliy.mvp.view.news.NewsFragment
import com.example.peopledaliy.mvp.view.news.NewsValuesActivity
import com.example.peopledaliy.mvp.view.people.RecommendFragment
import com.example.peopledaliy.mvp.view.people.adapter.FollowFragment
import com.example.peopledaliy.mvp.view.video.SmallVideoFragment
import dagger.Component

@Component(modules = [LREModules::class])
interface LREComponent {
    fun injectNewsFragment(fragment: NewsFragment)

    fun injectContentNewsFragment(fragment: ContentNewsFragment)

    fun injectLiveFragment(fragment: LiveFragment)

    fun injectNewsValuesActivity(activity: NewsValuesActivity)

    fun injectRecommendFragment(recommendFragment: RecommendFragment)

    fun injectSmallVideoFragment(smallVideoFragment: SmallVideoFragment)

    fun injectFollowFragment(followFragment: FollowFragment)

}