package com.example.peopledaliy.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class UploadRetrofit private constructor() : HttpImpl {

    companion object {
        private var retrofit: Retrofit? = null
    }
    override fun getRetrofit(): Retrofit? {
        return retrofit
    }
    public  class Build{

       private var retrofit: Retrofit? = null
        private fun createRetrofit() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okBuild = OkHttpClient.Builder()
            okBuild.addInterceptor(interceptor)
            okBuild.connectTimeout((30 * 1000).toLong(), TimeUnit.MILLISECONDS)
            okBuild.writeTimeout((300 * 1000).toLong(), TimeUnit.MILLISECONDS)
            okBuild.readTimeout((30 * 1000).toLong(), TimeUnit.MILLISECONDS)
            val builder = Retrofit.Builder()
            builder.client(okBuild.build())
            builder.baseUrl(Api.BaseUrl)
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            builder.addConverterFactory(GsonConverterFactory.create())
            retrofit = builder.build()
        }

        fun build(): UploadRetrofit {
            return UploadRetrofit()
        }

        init {
            createRetrofit()
        }
    }
}