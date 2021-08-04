package com.example.peopledaliy.mvp.view.people.adapter

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peopledaliy.App
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.view.BaseFragment
import com.example.peopledaliy.mvp.contract.LREContract
import com.example.peopledaliy.mvp.di.DaggerLREComponent
import com.example.peopledaliy.mvp.di.LREModules
import com.example.peopledaliy.mvp.model.HttpCode
import com.example.peopledaliy.mvp.model.entity.FollowEntity
import com.example.peopledaliy.mvp.model.entity.UserEntity
import com.example.peopledaliy.mvp.model.entity.UserFollowEntity
import com.example.peopledaliy.mvp.presenter.LREPresenter
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import java.util.*
import kotlin.collections.HashMap

class FollowFragment : BaseFragment<LREPresenter>(), LREContract.ILREView,
    OnRefreshLoadMoreListener {
    private var follow_smart: SmartRefreshLayout? = null
    private var follow_news_rv: RecyclerView? = null
    private var headView: View? = null
    private var follow_author_rv: RecyclerView? = null
    private var author: LinearLayoutManager? = null
    private var news: LinearLayoutManager? = null
    private var followNewsAdapter: FollowNewsAdapter? = null
    private var userFollowAdapter: UserFollowAdapter? = null
    override fun bindLayout(): Int {
        return R.layout.fragment_follow
    }

    override fun initView() {
        follow_smart = f(R.id.follow_smart)
        follow_news_rv = f(R.id.follow_news_rv)
        headView = LayoutInflater.from(getContext()).inflate(R.layout.view_follow_head, null)
        follow_author_rv = headView!!.findViewById(R.id.follow_author_rv)
        follow_smart!!.setEnableLoadMore(true)
        follow_smart!!.setEnableRefresh(true)
        follow_smart!!.setRefreshHeader(ClassicsHeader(getContext()))
        follow_smart!!.setRefreshFooter(ClassicsFooter(getContext()))
        follow_smart!!.setOnRefreshLoadMoreListener(this)
        news = LinearLayoutManager(getContext())
        news!!.orientation = LinearLayoutManager.VERTICAL
        follow_news_rv!!.layoutManager = news
        author = LinearLayoutManager(getContext())
        author!!.orientation = LinearLayoutManager.HORIZONTAL
        follow_author_rv!!.setLayoutManager(author)
        userFollowAdapter = UserFollowAdapter()
        followNewsAdapter = FollowNewsAdapter()
        followNewsAdapter!!.addHeaderView(headView!!)
        follow_news_rv!!.adapter = followNewsAdapter
        follow_author_rv!!.setAdapter(userFollowAdapter)
    }

    override fun initData() {
        val entity: UserEntity =
            App.getInstance()!!.getDaoSession()!!.queryBuilder(UserEntity::class.java).list().get(0)
        val newsMap: MutableMap<String, Any> = HashMap()
        newsMap["code"] = HttpCode.FOLLOWNEWSCODE
        newsMap["user_id"] = entity.user_id
        val followMap: MutableMap<String, Any> = HashMap()
        followMap["code"] = HttpCode.USERFOLLOWCODE
        followMap["user_id"] = entity.user_id
        p!!.requestAll(followMap as HashMap<String, Any>, newsMap as HashMap<String, Any>)
    }

    override fun inject() {
        DaggerLREComponent.builder().lREModules(LREModules(this))
            .build().injectFollowFragment(this)
    }

    override fun refreshAll(entity: BaseEntity?) {
        if (entity is FollowEntity) {
            val followEntity: FollowEntity = entity as FollowEntity
            followNewsAdapter!!.setNewInstance(followEntity.getValues() as MutableList<FollowEntity.Values>?)
        } else {
            val userFollowEntity: UserFollowEntity = entity as UserFollowEntity
            userFollowAdapter!!.setNewInstance(userFollowEntity.getValues() as MutableList<UserFollowEntity.Values>?)
        }
    }

    override fun refreshRecyckerView(entity: BaseEntity?) {
        val followEntity: FollowEntity = entity as FollowEntity
        if (followEntity.getValues() == null) {
            return
        }
        followNewsAdapter!!.setNewInstance(followEntity.getValues() as MutableList<FollowEntity.Values>?)
    }

    override fun loadMoreRecyclerView(entity: BaseEntity?) {
        val followEntity: FollowEntity = entity as FollowEntity
        if (followEntity.getValues() == null) {
            return
        }
        followNewsAdapter!!.addData(followEntity.getValues()!!)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        refreshLayout.finishLoadMore()
        val entity: UserEntity =
            App.getInstance()!!.getDaoSession()!!.queryBuilder(UserEntity::class.java).list().get(0)
        val newsMap: MutableMap<String, Any> = HashMap()
        newsMap["code"] = HttpCode.FOLLOWNEWSCODE
        newsMap["user_id"] = entity.user_id
        p!!.requestLoad(newsMap)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        refreshLayout.finishRefresh()
        val entity: UserEntity =
            App.getInstance()!!.getDaoSession()!!.queryBuilder(UserEntity::class.java).list().get(0)
        val newsMap: MutableMap<String, Any> = HashMap()
        newsMap["code"] = HttpCode.FOLLOWNEWSCODE
        newsMap["user_id"] = entity.user_id
        p!!.requestRefresh(newsMap)
    }
}
