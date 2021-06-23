package com.example.peopledaliy.mvp.view.user

import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.view.BaseFragment
import com.example.peopledaliy.mvp.contract.LoginContract
import com.example.peopledaliy.mvp.di.DaggerLoginComponent
import com.example.peopledaliy.mvp.di.LoginModules
import com.example.peopledaliy.mvp.model.HttpCode
import com.example.peopledaliy.mvp.model.LoginModel
import com.example.peopledaliy.mvp.presenter.LoginPresenter

class RegisterFragment : BaseFragment<LoginPresenter>(),LoginContract.ILoginView {
    var phone: EditText?=null
    var pwd: EditText?=null
    var bt_login: Button?=null
    override fun bindLayout(): Int {
        return R.layout.fragment_register
    }

    override fun initView() {
        phone = f<EditText>(R.id.user_phone)
        pwd = f<EditText>(R.id.user_pwd)
        bt_login = f<Button>(R.id.bt_login)
        f<LinearLayout>(R.id.register_ll).setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.ll,LoginFragment()).commit()
        }
    }

    override fun initData() {
        bt_login!!.setOnClickListener {
            p!!.requestLogin(phone!!.text.toString().toLong(),pwd!!.text.toString(), HttpCode.REGISTCODE)
        }
    }

    override fun inject() {
        DaggerLoginComponent.builder().loginModules(LoginModules(this)).build().inject(this)
    }

    override fun refreshView(entity: BaseEntity) {
        if (entity.status.equals("200")){
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.ll,LoginFragment()).commit()
        }
    }

}