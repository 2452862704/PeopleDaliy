package com.example.peopledaliy.mvp.view

import android.graphics.Color
import android.view.View
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.base.view.BaseAcyivity
import com.example.peopledaliy.mvp.contract.FrameContract
import com.example.peopledaliy.mvp.di.DaggerFrameComponent
import com.example.peopledaliy.mvp.di.FrameModules
import com.example.peopledaliy.mvp.presenter.FramePresenter
import com.example.peopledaliy.mvp.view.live.LiveFragment
import com.example.peopledaliy.mvp.view.news.NewsFragment
import com.example.peopledaliy.mvp.view.people.PeopleFragment
import com.example.peopledaliy.mvp.view.user.UserFragment
import com.example.peopledaliy.mvp.view.video.VideoFragment
import com.example.peopledaliy.widget.BottomButton


class FrameActivity : BaseAcyivity<FramePresenter>(), FrameContract.IFrameView, BottomButton.SelectListener {
    var userFragment: UserFragment? = null
    var newsFragment: NewsFragment? = null
    var videoFragment: VideoFragment? = null
    var peopleFragment: PeopleFragment? = null
    var liveFragment: LiveFragment? = null
    var frame_news_btn: BottomButton?=null
    var frame_pepole_btn: BottomButton?=null
    var frame_video_btn: BottomButton?=null
    var frame_live_btn: BottomButton?=null
    var frame_user_btn: BottomButton?=null
    override fun addStatusView(): View? {
        return f(R.id.frame_linear)
    }

    override fun bindLayout(): Int {
        return R.layout.activity_first
    }

    override fun initView() {
        frame_news_btn = f<BottomButton>(R.id.frame_news_btn)
        frame_pepole_btn = f<BottomButton>(R.id.frame_pepole_btn)
        frame_video_btn = f<BottomButton>(R.id.frame_video_btn)
        frame_live_btn = f<BottomButton>(R.id.frame_live_btn)
        frame_user_btn = f<BottomButton>(R.id.frame_user_btn)
        frame_news_btn!!.setSelColor(Color.RED)
            .setNomalColor(Color.GRAY)
            .setShowPoint(false)
            .setNomalImg(R.mipmap.res_7icon_216)
            .setSelImg(R.mipmap.res_7icon_211)
            .setContent("新闻")
        frame_pepole_btn!!.setSelColor(Color.RED)
            .setNomalColor(Color.GRAY)
            .setShowPoint(false)
            .setNomalImg(R.mipmap.res_7icon_217)
            .setSelImg(R.mipmap.res_7icon_212)
            .setContent("人民号")
        frame_video_btn!!.setSelColor(Color.RED)
            .setNomalColor(Color.GRAY)
            .setShowPoint(false)
            .setNomalImg(R.mipmap.res_7icon_218)
            .setSelImg(R.mipmap.res_7icon_213)
            .setContent("视频")
        frame_live_btn!!.setSelColor(Color.RED)
            .setNomalColor(Color.GRAY)
            .setShowPoint(false)
            .setNomalImg(R.mipmap.res_7icon_219)
            .setSelImg(R.mipmap.res_7icon_214)
            .setContent("直播")
        frame_user_btn!!.setSelColor(Color.RED)
            .setNomalColor(Color.GRAY)
            .setShowPoint(false)
            .setNomalImg(R.mipmap.res_7icon)
            .setSelImg(R.mipmap.res_7icon_215)
            .setContent("我的")
        frame_news_btn!!.setListener(this)
        frame_pepole_btn!!.setListener(this)
        frame_video_btn!!.setListener(this)
        frame_live_btn!!.setListener(this)
        frame_user_btn!!.setListener(this)
    }

    override fun initData() {

    }

    override fun inject() {
        DaggerFrameComponent.builder().frameModules(FrameModules(this)).build().inject(this)
    }

    override fun refresh(resultMap: Map<String, Any>) {

    }

    override fun onSelect(id: Int) {
        hideFragment();
        showFragment(id);
    }
    fun hideFragment(){
        setStatuesColor(resources.getColor(R.color.white))
        val ft = supportFragmentManager.beginTransaction()
        if (userFragment!=null){
            ft.hide(userFragment!!)
        }
        if (newsFragment != null){
            ft.hide(newsFragment!!)
        }
        if (peopleFragment != null){
            ft.hide(peopleFragment!!)
        }
        if (videoFragment != null){
            ft.hide(videoFragment!!)
        }
        ft.commit()
    }
    fun showFragment(id:Int){
        val ft = supportFragmentManager.beginTransaction()
        when(id){
            R.id.frame_news_btn->{
                frame_news_btn!!.selectCheck()
                frame_pepole_btn!!.clearSelect()
                frame_video_btn!!.clearSelect()
                frame_live_btn!!.clearSelect()
                frame_user_btn!!.clearSelect()
                if (newsFragment==null){
                    newsFragment= NewsFragment()
                    ft.add(R.id.fragment_linear, newsFragment!!)
                }else{
                    ft.show(newsFragment!!)
                }
            }
            R.id.frame_pepole_btn->{
                frame_news_btn!!.clearSelect()
                frame_video_btn!!.clearSelect()
                frame_live_btn!!.clearSelect()
                frame_user_btn!!.clearSelect()
                if (peopleFragment==null){
                    peopleFragment= PeopleFragment()
                    ft.add(R.id.fragment_linear, peopleFragment!!)
                }else{
                    ft.show(peopleFragment!!)
                }
            }
            R.id.frame_video_btn->{
                frame_news_btn!!.clearSelect()
                frame_pepole_btn!!.clearSelect()
                frame_live_btn!!.clearSelect()
                frame_user_btn!!.clearSelect()
                if (videoFragment==null){
                    videoFragment= VideoFragment()
                    ft.add(R.id.fragment_linear, videoFragment!!)
                }else{
                    ft.show(videoFragment!!)
                }
            }
            R.id.frame_live_btn->{
                frame_news_btn!!.clearSelect()
                frame_pepole_btn!!.clearSelect()
                frame_video_btn!!.clearSelect()
                frame_user_btn!!.clearSelect()
                if (liveFragment==null){
                    liveFragment= LiveFragment()
                    ft.add(R.id.fragment_linear, liveFragment!!)
                }else{
                    ft.show(liveFragment!!)
                }
            }
            R.id.frame_user_btn->{
                setStatuesColor(resources.getColor(R.color.red))
                frame_news_btn!!.clearSelect()
                frame_pepole_btn!!.clearSelect()
                frame_video_btn!!.clearSelect()
                frame_live_btn!!.clearSelect()
                if (userFragment==null){
                    userFragment= UserFragment()
                    ft.add(R.id.fragment_linear, userFragment!!)
                }else{
                    ft.show(userFragment!!)
                }
            }
        }
        ft.commit()
    }
}