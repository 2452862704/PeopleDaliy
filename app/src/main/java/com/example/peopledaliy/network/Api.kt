package com.example.peopledaliy.network

import com.example.peopledaliy.mvp.model.entity.ResponseUserEntity
import com.example.peopledaliy.mvp.model.entity.ToKenEntity
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    //    String BaseUrl = "http://118.195.161.134:8088/";//生产环境地址
    companion object{
        var BaseUrl = "http://118.195.161.134:8066/"
        var FileUrl = "http://118.195.161.134:8066/fileDownload?fileName="
    }

    @POST("sysToken/getToken")
    fun requestToken(@Body body: RequestBody?): Observable<ToKenEntity>

    @POST("sysUser/loginUser")
    fun requestLogin(@Body body: RequestBody?): Observable<ResponseUserEntity>

    @POST("sysUser/registerUser")
    fun requestRegister(@Body body: RequestBody?): Observable<ResponseUserEntity>

}