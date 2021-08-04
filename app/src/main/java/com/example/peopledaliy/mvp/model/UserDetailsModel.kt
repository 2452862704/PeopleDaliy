package com.example.peopledaliy.mvp.model

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.model.BaseModel
import com.example.peopledaliy.mvp.contract.UserDetailsContract
import com.example.peopledaliy.mvp.model.entity.ResponseUserEntity
import com.example.peopledaliy.mvp.model.entity.UpdateUserEntity
import com.example.peopledaliy.mvp.model.entity.UploadFileEntity
import com.example.peopledaliy.network.Api
import com.example.peopledaliy.network.ChangeFunction
import com.example.peopledaliy.network.HttpFactory
import com.example.peopledaliy.network.HttpType
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UserDetailsModel:BaseModel(),UserDetailsContract.IUserDetailsModel {
    override fun requestUser(map: Map<String, Any>): Observable<BaseEntity> {
        return HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!.getRetrofit()!!
            .create(Api::class.java).requestUser(createBody(map))
            .map(ChangeFunction<ResponseUserEntity>())
    }

    override fun updateUser(map: Map<String, Any>): Observable<BaseEntity> {
        return HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!.getRetrofit()!!
            .create(Api::class.java).requestUpdateUser(createBody(map))
            .map(ChangeFunction<UpdateUserEntity>())
    }

    override fun uploadHead(map: Map<String, Any>): Observable<UploadFileEntity> {
        val str = map["file"] as String?
        val file = File(str)
        val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val builder = MultipartBody.Builder()
        builder.addFormDataPart("file", str!!.substring(str!!.lastIndexOf("/") + 1), requestBody)
        return HttpFactory.instance!!.factory(HttpType.UPLOADTYPE)!!.getRetrofit()!!
            .create(Api::class.java)
            .requestUploadHead(builder.build().parts())
    }
}