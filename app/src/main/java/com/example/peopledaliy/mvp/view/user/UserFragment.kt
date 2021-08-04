package com.example.peopledaliy.mvp.view.user

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.view.BaseFragment
import com.example.peopledaliy.mvp.contract.UserContract
import com.example.peopledaliy.mvp.model.entity.UserItemEntity
import com.example.peopledaliy.mvp.model.entity.UserMenuEntity
import com.example.peopledaliy.mvp.presenter.UserPresenter
import com.example.peopledaliy.widget.LinearDividerItemDecoration
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.di.DaggerUserComponent
import com.example.peopledaliy.mvp.di.UserModules

class UserFragment : BaseFragment<UserPresenter>(), UserContract.IUserView, View.OnClickListener {
    var start_go: TextView?=null
    override fun bindLayout(): Int {
        return R.layout.fragment_user
    }

    override fun initView() {
        start_go = f<TextView>(R.id.start_go)
    }

    override fun initData() {
        start_go!!.setOnClickListener {
            if (activity!!.getSharedPreferences("user",Context.MODE_PRIVATE).getBoolean("flag",false)){
                startActivity(Intent(context,HeadActivity::class.java))
            }else{
                startActivity(Intent(context,UserActivity::class.java))
            }
        }
    }

    override fun inject() {
        DaggerUserComponent.builder().userModules(UserModules(this)).build().inject(this)
    }

    override fun refresh(entity: BaseEntity) {

    }

    override fun refreshMenu(menues: MutableList<UserMenuEntity>) {

    }

    override fun refreshItemes(itemes: MutableList<UserItemEntity>) {
    }

    override fun onClick(v: View?) {

    }



}