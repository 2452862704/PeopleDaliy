package com.example.peopledaliy.mvp.view.user

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.blankj.utilcode.util.LogUtils
import com.example.peopledaliy.App
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.view.BaseAcyivity
import com.example.peopledaliy.mvp.contract.UserDetailsContract
import com.example.peopledaliy.mvp.di.DaggerUserDetailsComponent
import com.example.peopledaliy.mvp.di.UserDetailsModules
import com.example.peopledaliy.mvp.model.entity.ResponseUserEntity
import com.example.peopledaliy.mvp.model.entity.UserEntity
import com.example.peopledaliy.mvp.presenter.UserDetailsPresenter
import com.example.peopledaliy.network.LoadImage
import com.example.peopledaliy.widget.PhotoPop
import com.example.peopledaliy.widget.SexPop
import java.util.*

class HeadActivity : BaseAcyivity<UserDetailsPresenter>(),UserDetailsContract.IUserDetailsView,View.OnClickListener,SexPop.SexCallBack{
    private var head_back_img: ImageView? = null
    private  var user_head_img:android.widget.ImageView? = null
    private var user_head_relative: RelativeLayout? = null
    private  var user_nick_relative:RelativeLayout? = null
    private  var user_sex_relative:RelativeLayout? = null
    private var user_nick_tv: TextView? = null
    private  var user_sex_tv:TextView? = null
    private var photoPop: PhotoPop? = null
    private var sexPop: SexPop? = null
    override fun addStatusView(): View? {
        return f(R.id.user_details_root)
    }

    override fun bindLayout(): Int {
        return R.layout.activity_head
    }

    override fun initView() {
        photoPop = PhotoPop(this)
        sexPop = SexPop(this, this)
        head_back_img = f(R.id.head_back_img)
        user_head_img = f(R.id.user_head_img)
        user_head_relative = f(R.id.user_head_relative)
        user_nick_relative = f(R.id.user_nick_relative)
        user_sex_relative = f(R.id.user_sex_relative)
        user_nick_tv = f(R.id.user_nick_tv)
        user_sex_tv = f(R.id.user_sex_tv)
        head_back_img!!.setOnClickListener(this)
        user_head_relative!!.setOnClickListener(this)
        user_nick_relative!!.setOnClickListener(this)
        user_sex_relative!!.setOnClickListener(this)
        requestPermissions(arrayOf("android.permission.CAMERA","android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE"),999)
    }

    override fun initData() {
        val entity: UserEntity =
            App.getInstance()!!.getDaoSession()!!.queryBuilder(UserEntity::class.java)
                .list().get(0)
        val map: MutableMap<String, Any> = HashMap()
        map["user_id"] = entity.user_id
        p!!.requestUserDetails(map)
    }

    override fun inject() {
        DaggerUserDetailsComponent.builder().userDetailsModules(UserDetailsModules(this)).build().inject(this)
    }

    override fun refreshView(entity: BaseEntity) {
        if (entity is ResponseUserEntity) {
            App.getInstance()!!.getDaoSession()!!.deleteAll(UserEntity::class.java)
            val responseUserEntity: ResponseUserEntity = entity as ResponseUserEntity
            val userEntity: UserEntity? = responseUserEntity.getValues()
            App.getInstance()!!.getDaoSession()!!.insert(userEntity)
            updateUI(userEntity!!)
        }
    }
    private fun updateUI(userEntity: UserEntity) {
        if (!userEntity.user_img.isEmpty()) LoadImage().loadCircleImg(
            userEntity.user_img,
            user_head_img!!
        )
        user_nick_tv!!.text = userEntity.user_name
        var sex_tv = ""
        if (userEntity.user_sex.equals("")) sex_tv = "保密"
        if (userEntity.user_sex.equals("0")) sex_tv = "男"
        if (userEntity.user_sex.equals("1")) sex_tv = "女"
        user_sex_tv!!.text = sex_tv
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (!data!!.getStringExtra("nick")!!.isEmpty()) {
                val map: MutableMap<String, Any> = HashMap()
                val entity =
                    App.getInstance()!!.getDaoSession()!!.queryBuilder(UserEntity::class.java)
                        .list()[0]
                map["user_id"] = entity.user_id
                map["user_img"] = entity.user_img
                map["user_integral"] = entity.user_integral
                map["user_name"] = data.getStringExtra("nick")!!
                map["user_phone"] = entity.user_phone
                map["user_pwd"] = entity.user_pwd
                map["user_sex"] = entity.user_sex
                p!!.requestUpdateUser(map)
            } else {
                val path = data.getStringExtra("path")
                p!!.uploadHead(path!!)
            }
        }
    }
    override fun onClick(v: View?) {
        val id = v!!.id
        if (R.id.head_back_img === id) finish() else if (R.id.user_head_relative === id) {
            photoPop!!.show(window.decorView.rootView)
        } else if (R.id.user_nick_relative === id) {
            val intent = Intent(this, UserNameActivity::class.java)
            startActivityForResult(intent, 100)
        } else if (R.id.user_sex_relative === id) {
            sexPop!!.show(window.decorView.rootView)
        }
    }

    override fun sexCall(sexCode: Int) {
        val map: MutableMap<String, Any> = HashMap()
        val entity = App.getInstance()!!.getDaoSession()!!.queryBuilder(UserEntity::class.java)
            .list()[0]
        map["user_id"] = entity.user_id
        map["user_img"] = entity.user_img
        map["user_integral"] = entity.user_integral
        map["user_name"] = entity.user_name
        map["user_phone"] = entity.user_phone
        map["user_pwd"] = entity.user_pwd
        map["user_sex"] = "" + sexCode
        p!!.requestUpdateUser(map)
    }

}