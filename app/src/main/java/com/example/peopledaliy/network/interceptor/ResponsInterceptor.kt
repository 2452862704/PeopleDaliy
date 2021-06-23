package com.example.peopledaliy.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ResponsInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code() == 200) return response
        val builder = Response.Builder()
        builder.request(response.request())
        builder.code(200)
        builder.message(response.message())
        builder.body(response.body())
        builder.headers(response.headers())
        builder.protocol(response.protocol())
        return builder.build()
    }
}