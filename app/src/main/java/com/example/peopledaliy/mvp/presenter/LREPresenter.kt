package com.example.peopledaliy.mvp.presenter

import android.util.Log
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.presenter.BasePresenter
import com.example.peopledaliy.mvp.contract.LREContract
import com.example.peopledaliy.mvp.model.HttpCode
import com.example.peopledaliy.mvp.model.entity.NewsEntity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

 class LREPresenter @Inject constructor(m:LREContract.ILREModel, v:LREContract.ILREView):BasePresenter<LREContract.ILREModel,LREContract.ILREView>(m, v) {
    private var page = 1
    private val ob:LoadMoreOb? = null
    //请求全部数据方法
    fun requestAll(vararg maps: HashMap<String, Any>) {
        //针对列表接口中的请求参数中的page进行二次拼接操作
        for (i in 0 until maps.size) {
            val code = maps[i].get("code")
            when (code) {
                HttpCode.CHANNELNEWS -> maps[i]["page"] = page
                HttpCode.FOLLOWNEWSCODE -> maps[i]["page"] = page
                HttpCode.USERFOLLOWCODE -> maps[i]["page"] = page
                HttpCode.VIDEONEWSCODE -> maps[i]["page"] = page
                HttpCode.LIVECODE -> maps[i]["page"] = page
                HttpCode.SELCOMMENTCODE -> maps[i]["page"] = page
                HttpCode.SELLIVECOMMENTCODE -> maps[i]["page"] = page
                HttpCode.TEXTBANNERCODE -> maps[i]["page"] = page
                HttpCode.IMAGERBANNERCODE -> maps[i]["page"] = page
            }
        }
        m.requestAll(*maps)!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this)
    }
    fun requestRefresh(map: MutableMap<String, Any>) {
        page = 1
        map["page"] = page
        m.request_refresh_load(map)!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this)
    }
    fun requestLoad(map: MutableMap<String, Any>) {
        page += 1
        map["page"] = page
        m.request_refresh_load(map)!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this)
    }
     fun requestLoa(map: MutableMap<String, Any>) {
         map["page"] = page
         m.request_refresh_load(map)!!.subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(this)
     }
    override fun onNext(t: BaseEntity) {
        if (t is NewsEntity) {
            //新闻列表返回值
            v.refreshRecyckerView(t)
            return
        }
        v.refreshAll(t)
    }
    fun loadMoreNext(entity: BaseEntity?) {
        v.loadMoreRecyclerView(entity)
    }
    class LoadMoreOb(private val lrePresenter: LREPresenter) : Observer<BaseEntity> {
        override fun onSubscribe(d: Disposable) {
            lrePresenter.onSubscribe(d)
        }

        override fun onNext(entity: BaseEntity) {
            lrePresenter.loadMoreNext(entity)
        }

        override fun onError(e: Throwable) {
            lrePresenter.onError(e)
        }

        override fun onComplete() {
            lrePresenter.onComplete()
        }
    }
}