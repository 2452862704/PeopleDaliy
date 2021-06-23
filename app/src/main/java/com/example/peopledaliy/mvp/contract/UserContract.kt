package com.example.peopledaliy.mvp.contract

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.model.IModel
import com.example.peopledaliy.mvp.base.view.IView
import com.example.peopledaliy.mvp.model.entity.UserItemEntity
import com.example.peopledaliy.mvp.model.entity.UserMenuEntity
import io.reactivex.Observable

interface UserContract {
    interface IUserModel : IModel {
        fun requestUser(map: Map<String, Any>): Observable<BaseEntity>?
    }

    interface IUserView : IView {
        //用户信息回调方法,今日签到任务列表
        fun refresh(entity: BaseEntity)

        //用户菜单界面
        fun refreshMenu(menues: MutableList<UserMenuEntity>)

        //积分商城等item
        fun refreshItemes(itemes: MutableList<UserItemEntity>)
    }
}