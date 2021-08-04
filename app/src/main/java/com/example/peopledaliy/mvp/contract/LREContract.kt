package com.example.peopledaliy.mvp.contract

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.model.IModel
import com.example.peopledaliy.mvp.base.view.IView
import io.reactivex.Observable


interface LREContract {
     interface ILREModel : IModel {
        //列表界面中无论多少接口全部合并请求方法
        fun requestAll(vararg maps: HashMap<String, Any>): Observable<BaseEntity>
        fun request_refresh_load(map: MutableMap<String, Any>): Observable<BaseEntity>?
    }

    //LREView
    interface ILREView : IView {
        //全部数据请求返回方法
        fun refreshAll(entity: BaseEntity?)

        //下拉刷新数据返回
        fun refreshRecyckerView(entity: BaseEntity?)

        //上拉加载数据返回
        fun loadMoreRecyclerView(entity: BaseEntity?)
    }
}