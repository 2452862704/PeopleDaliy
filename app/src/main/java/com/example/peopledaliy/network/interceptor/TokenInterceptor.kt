package com.example.peopledaliy.network.interceptor

import com.blankj.utilcode.util.LogUtils
import com.example.peopledaliy.utils.SpUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class TokenInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //先获取原有Request
        val oldRequest = chain.request()
        //配置添加过token的Request
        val build = Request.Builder()
        build.url(oldRequest.url())
        build.headers(oldRequest.headers())
        build.addHeader("token", SpUtils().readData("token"))
        LogUtils.i("json:"+SpUtils().readData("token"))
        build.post(oldRequest.body())
        val request = build.build()
        return chain.proceed(request)
    }
}