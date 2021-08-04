package com.example.peopledaliy.mvp.presenter

import com.example.peopledaliy.App
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.presenter.BasePresenter
import com.example.peopledaliy.mvp.contract.UserDetailsContract
import com.example.peopledaliy.mvp.model.entity.UpdateUserEntity
import com.example.peopledaliy.mvp.model.entity.UploadFileEntity
import com.example.peopledaliy.mvp.model.entity.UserEntity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class UserDetailsPresenter @Inject constructor(m:UserDetailsContract.IUserDetailsModel, v:UserDetailsContract.IUserDetailsView):BasePresenter<UserDetailsContract.IUserDetailsModel, UserDetailsContract.IUserDetailsView>(m, v) {
    private var file: String? = null
    fun requestUserDetails(map: Map<String, Any>) {
        if (map == null) return
        m.requestUser(map).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this)
    }
    fun requestUpdateUser() {
        val map: MutableMap<String, Any> = HashMap()
        val entity: UserEntity =
            App.getInstance()!!.getDaoSession()!!.queryBuilder(UserEntity::class.java)
                .list().get(0)
        map["user_id"] = entity.user_id
        map["user_img"] = file!!.substring(file!!.lastIndexOf("/") + 1)
        map["user_integral"] = entity.user_integral
        map["user_name"] = entity.user_name
        map["user_phone"] = entity.user_phone
        map["user_pwd"] = entity.user_pwd
        map["user_sex"] = entity.user_sex
        requestUpdateUser(map)
    }
    fun requestUpdateUser(map: Map<String, Any>) {
        if (map == null) return
        m.updateUser(map).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this)
    }
    fun uploadHead(file: String) {
        if (file.isEmpty()) return
        this.file = file
        val map: MutableMap<String, Any> = HashMap()
        map["file"] = file
        m.uploadHead(map).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(UploadOb(this))
    }
    override fun onNext(t: BaseEntity) {
        if (t is UpdateUserEntity) {
            val userEntity =
                App.getInstance()!!.getDaoSession()!!.queryBuilder(UserEntity::class.java)
                    .list()[0]
            val map: MutableMap<String, Any> = HashMap()
            map["user_id"] = userEntity.user_id
            requestUserDetails(map)
            return
        }
        v.refreshView(t)
    }
    private class UploadOb(private val presenter: UserDetailsPresenter) :
        Observer<UploadFileEntity> {
        override fun onSubscribe(d: Disposable) {
            presenter.onSubscribe(d)
        }

        override fun onNext(uploadFileEntity: UploadFileEntity) {
            presenter.requestUpdateUser()
        }

        override fun onError(e: Throwable) {
            presenter.onError(e)
        }

        override fun onComplete() {
            presenter.onComplete()
        }
    }
}