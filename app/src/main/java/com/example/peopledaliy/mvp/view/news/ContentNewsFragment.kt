package com.example.peopledaliy.mvp.view.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.view.BaseVpFragment
import com.example.peopledaliy.mvp.contract.LREContract
import com.example.peopledaliy.mvp.di.DaggerLREComponent
import com.example.peopledaliy.mvp.di.LREModules
import com.example.peopledaliy.mvp.model.HttpCode
import com.example.peopledaliy.mvp.model.entity.ImageBannerEntity
import com.example.peopledaliy.mvp.model.entity.NewsEntity
import com.example.peopledaliy.mvp.presenter.LREPresenter
import com.example.peopledaliy.mvp.view.news.adapter.ContentNewsAdapter
import com.example.peopledaliy.mvp.view.news.adapter.ImageBannerAdapter
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import io.reactivex.Observable
import okhttp3.RequestBody
import java.util.*
import kotlin.collections.HashMap

  class ContentNewsFragment constructor(val channel_id: Long) : BaseVpFragment<LREPresenter>(), LREContract.ILREView, OnRefreshLoadMoreListener, OnItemClickListener {
    private var content_news_smart: SmartRefreshLayout? = null
    private var content_news_rv: RecyclerView? = null
    private var headView: View? = null
    private var headVp: ViewPager? = null
    private var vpAdapter: ImageBannerAdapter? = null
    private var adapter: ContentNewsAdapter? = null
    override fun bindLayout(): Int {
        return R.layout.fragment_content_news
    }

    override fun initView() {
        headView = LayoutInflater.from(getContext()).inflate(R.layout.view_news_banner, null)
        headVp = headView!!.findViewById(R.id.news_banner_vp)
        content_news_smart = f(R.id.content_news_smart)
        content_news_smart!!.setEnableRefresh(true)
        content_news_smart!!.setEnableLoadMore(true)
        content_news_smart!!.setRefreshHeader(ClassicsHeader(getContext()))
        content_news_smart!!.setRefreshFooter(ClassicsFooter(getContext()))
        content_news_smart!!.setOnRefreshLoadMoreListener(this)
        content_news_rv = f(R.id.content_news_rv)
        val manager = LinearLayoutManager(getContext())
        manager.orientation = LinearLayoutManager.VERTICAL
        content_news_rv!!.layoutManager = manager
        adapter = ContentNewsAdapter()
        adapter!!.addHeaderView(headView!!)
        content_news_rv!!.adapter = adapter
        adapter!!.setOnItemClickListener(this)
    }

    override fun initData() {
        val map: MutableMap<String, Any> = HashMap()
        map["code"] = HttpCode.CHANNELNEWS
        map["channel_id"] = channel_id
        val bannerMap: MutableMap<String, Any> = HashMap()
        bannerMap["code"] = HttpCode.IMAGERBANNERCODE
        p!!.requestAll(bannerMap as HashMap<String, Any>, map as HashMap<String, Any>)
    }

    override fun inject() {
        DaggerLREComponent.builder().lREModules(LREModules(this)).build()
            .injectContentNewsFragment(this)
    }

    override fun refreshAll(entity: BaseEntity?) {
        if (entity is ImageBannerEntity) {
            //banner列表返回
            val bannerEntity: ImageBannerEntity = entity as ImageBannerEntity
            vpAdapter = bannerEntity.getValues()?.let { ImageBannerAdapter(it) }
            headVp!!.adapter = vpAdapter
        }
        if (entity is NewsEntity) {
            //新闻列表返回
            val newsEntity: NewsEntity = entity as NewsEntity
            adapter!!.setNewInstance(newsEntity!!.getValues() as MutableList<NewsEntity.Values>?)
        }
    }

    override fun refreshRecyckerView(entity: BaseEntity?) {
        val newsEntity: NewsEntity = entity as NewsEntity
        adapter!!.setNewInstance(newsEntity.getValues() as MutableList<NewsEntity.Values>?)
    }

    override fun loadMoreRecyclerView(entity: BaseEntity?) {
        val newsEntity: NewsEntity = entity as NewsEntity
        if (newsEntity.getValues() == null) {
            return
        }
        if (newsEntity.getValues()!!.size === 0) {
            return
        }
        val list: MutableList<NewsEntity.Values> = adapter!!.data
        list.addAll(newsEntity.getValues()!!)
        adapter!!.setNewInstance(list)
    }

      override fun onLoadMore(refreshLayout: RefreshLayout) {
//        refreshLayout.finishLoadMore()
//        val map: MutableMap<String, Any> = HashMap()
//        map["code"] = HttpCode.CHANNELNEWS
//        map["channel_id"] = channel_id
//        p!!.requestLoad(map)
          Log.i("fkt", "onLoadMore: a")
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        refreshLayout.finishRefresh()
        val map: MutableMap<String, Any> = HashMap()
        map["code"] = HttpCode.CHANNELNEWS
        map["channel_id"] = channel_id
        p!!.requestRefresh(map)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val values: NewsEntity.Values = adapter.getItem(position) as NewsEntity.Values
        val url: String? = values.news_url
        val id: Long = values.news_id
        val bundle = Bundle()
        bundle.putLong("news_id", id)
        bundle.putString("news_url", url)
        startPage(bundle, NewsValuesActivity::class.java)
    }
}
