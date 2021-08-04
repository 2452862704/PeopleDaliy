package com.example.peopledaliy.mvp.view.news

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.PhoneUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.peopledaliy.App
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.view.BaseFragment
import com.example.peopledaliy.mvp.contract.LREContract
import com.example.peopledaliy.mvp.di.DaggerLREComponent
import com.example.peopledaliy.mvp.di.LREModules
import com.example.peopledaliy.mvp.model.HttpCode
import com.example.peopledaliy.mvp.model.entity.ChannelEntity
import com.example.peopledaliy.mvp.model.entity.TextBannerEntity
import com.example.peopledaliy.mvp.model.entity.UserEntity
import com.example.peopledaliy.mvp.presenter.LREPresenter
import com.example.peopledaliy.mvp.view.news.adapter.NewsVpAdapter
import com.example.peopledaliy.mvp.view.news.adapter.TextBannerAdapter
import com.flyco.tablayout.SlidingTabLayout
import com.youth.banner.Banner
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.transformer.ZoomOutPageTransformer
import java.util.*
import kotlin.collections.ArrayList


 class NewsFragment : BaseFragment<LREPresenter>(),LREContract.ILREView,OnBannerListener<TextBannerEntity.Values>,View.OnClickListener {
     var tabLayout: SlidingTabLayout? = null
     var viewPager: ViewPager? = null
     var news_more_img: ImageView? = null
     var news_text_banner: Banner<TextBannerEntity.Values, TextBannerAdapter>? = null
     var textBannerAdapter: TextBannerAdapter? = null
     var newsVpAdapter: NewsVpAdapter? = null
     var fragments: ArrayList<Fragment>? = null
     val TAG = "NewsFragment"
    override fun bindLayout(): Int {
        return R.layout.fragment_news
    }

    override fun initView() {
        tabLayout = f(R.id.news_tab)
        viewPager = f(R.id.news_vp)
        news_more_img = f(R.id.news_more_img)
        news_text_banner = f(R.id.news_text_banner)
        news_more_img!!.setOnClickListener { v: View? -> onClick(v) }
    }

    override fun initData() {
        fragments = ArrayList()
        val users: List<UserEntity> = App.getInstance()!!.getDaoSession()!!.queryBuilder(UserEntity::class.java).list()
        val flag = users.size == 0
        val bannerMap: MutableMap<String, Any> = HashMap()
        bannerMap["code"] = HttpCode.TEXTBANNERCODE
        val channelMap: MutableMap<String, Any> = HashMap()
        channelMap["code"] = HttpCode.USERCHANNELCODE
        if (ActivityCompat.checkSelfPermission(activity!!, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        channelMap["imie"] = if (flag) PhoneUtils.getIMEI() else ""
        channelMap["userId"] = if (flag) 0 else users[0].user_id
        p!!.requestAll(bannerMap as HashMap<String, Any>)
        p!!.requestAll(channelMap as HashMap<String, Any>)
    }

    override fun inject() {
        DaggerLREComponent.builder().lREModules(LREModules(this)).build().injectNewsFragment(this)
    }

    override fun refreshAll(entity: BaseEntity?) {
        if (entity is TextBannerEntity) {
            val textBannerEntity = entity
            if (textBannerEntity.getValues() == null) {
                ToastUtils.showShort("文字广告数据丢失")
                return
            }
            textBannerAdapter = TextBannerAdapter(textBannerEntity.getValues())
            news_text_banner!!.setAdapter(textBannerAdapter)
                .setOrientation(Banner.VERTICAL)
                .setPageTransformer(ZoomOutPageTransformer())
                .setOnBannerListener(this)
        } else if (entity is ChannelEntity) {
            val channelEntity: ChannelEntity = entity as ChannelEntity
            if (channelEntity.getValues() == null) {
                ToastUtils.showShort("无频道数据")
                return
            }
            for (i in 0 until channelEntity.getValues()!!.size) {
                val contentNewsFragment =
                    ContentNewsFragment(channelEntity.getValues()!!.get(i).channel_id)
                fragments!!.add(contentNewsFragment)
            }
            newsVpAdapter = NewsVpAdapter(fragmentManager!!, fragments!!,
                channelEntity.getValues()!!
            )
            viewPager!!.adapter = newsVpAdapter
            tabLayout!!.setViewPager(viewPager)
        }
    }

    override fun refreshRecyckerView(entity: BaseEntity?) {

    }

    override fun loadMoreRecyclerView(entity: BaseEntity?) {

    }


    override fun OnBannerClick(data: TextBannerEntity.Values?, position: Int) {

    }

    override fun onClick(v: View?) {

    }
    override fun onStop() {
        super.onStop()
        news_text_banner!!.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        news_text_banner!!.destroy()
    }
}