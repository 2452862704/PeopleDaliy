package com.example.peopledaliy.mvp.presenter

import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.presenter.BasePresenter
import com.example.peopledaliy.mvp.contract.UserContract
import com.example.peopledaliy.mvp.model.entity.UserItemEntity
import com.example.peopledaliy.mvp.model.entity.UserMenuEntity
import java.util.*
import javax.inject.Inject

class UserPresenter @Inject constructor(m:UserContract.IUserModel, v:UserContract.IUserView):BasePresenter<UserContract.IUserModel,UserContract.IUserView>(m, v) {
    override fun onNext(t: BaseEntity) {

    }

    //初始化数据方法
    fun initData() {
        val menues: MutableList<UserMenuEntity> = ArrayList<UserMenuEntity>()
        menues.add(UserMenuEntity(R.mipmap.res_icon7_274, "消息", null))
        menues.add(UserMenuEntity(R.mipmap.res_icon7_275, "收藏", null))
        menues.add(UserMenuEntity(R.mipmap.res_icon7_277, "评论", null))
        menues.add(UserMenuEntity(R.mipmap.res_icon7_276, "历史", null))
        menues.add(UserMenuEntity(R.mipmap.res_icon7_276, "关注", null))
        menues.add(UserMenuEntity(R.mipmap.res_icon7_279, "下载", null))
        menues.add(UserMenuEntity(R.mipmap.res_icon7_280, "公益", null))
        menues.add(UserMenuEntity(R.mipmap.res_icon7_281, "问政", null))
        v.refreshMenu(menues)
        val itemes: MutableList<UserItemEntity> = ArrayList<UserItemEntity>()
        itemes.add(UserItemEntity("积分商城", null))
        itemes.add(UserItemEntity("夜间模式", null))
        itemes.add(UserItemEntity("意见反馈", null))
        itemes.add(UserItemEntity("更多设置", null))
        v.refreshItemes(itemes)
    }
}