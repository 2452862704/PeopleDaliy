package com.example.peopledaliy.mvp.view.video

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.view.BaseFragment
import com.example.peopledaliy.mvp.contract.LREContract
import com.example.peopledaliy.mvp.di.DaggerLREComponent
import com.example.peopledaliy.mvp.di.LREModules
import com.example.peopledaliy.mvp.model.HttpCode
import com.example.peopledaliy.mvp.model.entity.VideoNewsEntity
import com.example.peopledaliy.mvp.presenter.LREPresenter
import com.example.peopledaliy.mvp.view.video.adapter.SmallVideoAdapter
import com.example.peopledaliy.widget.VideoNewsItemDecoration
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import java.util.*
import kotlin.collections.HashMap

class SmallVideoFragment : BaseFragment<LREPresenter>(), LREContract.ILREView,
    OnRefreshLoadMoreListener {
    private var small_smart: SmartRefreshLayout? = null
    private var small_rv: RecyclerView? = null
    private var manager: GridLayoutManager? = null
    private var adapter: SmallVideoAdapter? = null
    override fun bindLayout(): Int {
        return R.layout.fragment_small_video
    }

    override fun initView() {
        small_smart = f(R.id.small_smart)
        small_rv = f(R.id.small_rv)
        small_smart!!.setEnableRefresh(true)
        small_smart!!.setEnableLoadMore(true)
        small_smart!!.setRefreshHeader(ClassicsHeader(getContext()))
        small_smart!!.setRefreshFooter(ClassicsFooter(getContext()))
        small_smart!!.setOnRefreshLoadMoreListener(this)
        manager = GridLayoutManager(getContext(), 2)
        manager!!.orientation = GridLayoutManager.VERTICAL
        small_rv!!.layoutManager = manager
        small_rv!!.addItemDecoration(VideoNewsItemDecoration(getContext()))
        adapter = SmallVideoAdapter()
        small_rv!!.adapter = adapter
    }

    override fun initData() {
        val map: MutableMap<String, Any> = HashMap()
        map["news_type"] = 0
        map["code"] = HttpCode.VIDEONEWSCODE
        p!!.requestAll(map as HashMap<String, Any>)
    }

    override fun inject() {
        DaggerLREComponent.builder().lREModules(LREModules(this))
            .build().injectSmallVideoFragment(this)
    }

    override fun refreshAll(entity: BaseEntity?) {
        val videoNewsEntity: VideoNewsEntity = entity as VideoNewsEntity
        if (videoNewsEntity.getValues() == null) {
            return
        }
        adapter!!.setNewInstance(videoNewsEntity.getValues() as MutableList<VideoNewsEntity.Values>?)
    }

    override fun refreshRecyckerView(entity: BaseEntity?) {
        val videoNewsEntity: VideoNewsEntity = entity as VideoNewsEntity
        if (videoNewsEntity.getValues() == null) {
            return
        }
        adapter!!.setNewInstance(videoNewsEntity.getValues() as MutableList<VideoNewsEntity.Values>?)
    }

    override fun loadMoreRecyclerView(entity: BaseEntity?) {
        val videoNewsEntity: VideoNewsEntity = entity as VideoNewsEntity
        if (videoNewsEntity.getValues() == null) {
            return
        }
        adapter!!.addData(videoNewsEntity.getValues()!!)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
//        refreshLayout.finishRefresh()
//        val map: MutableMap<String, Any> = HashMap()
//        map["news_type"] = 0
//        map["code"] = HttpCode.VIDEONEWSCODE
//        p!!.requestLoad(map)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        refreshLayout.finishRefresh()
        val map: MutableMap<String, Any> = HashMap()
        map["news_type"] = 0
        map["code"] = HttpCode.VIDEONEWSCODE
        p!!.requestRefresh(map)
    }
}