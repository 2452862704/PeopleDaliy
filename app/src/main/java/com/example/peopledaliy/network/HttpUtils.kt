package com.example.peopledaliy.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HttpUtils {
    var retrofit:Retrofit?=null
    init {
        retrofit=Retrofit.Builder()
            .baseUrl("")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .build()
    }
    companion object{
        val httpUtils:HttpUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            HttpUtils()
        }
    }
}