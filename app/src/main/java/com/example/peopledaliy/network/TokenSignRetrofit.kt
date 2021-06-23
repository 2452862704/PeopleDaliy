package com.example.peopledaliy.network

import com.example.peopledaliy.network.interceptor.ResponsInterceptor
import com.example.peopledaliy.network.interceptor.SignInterceptor
import com.example.peopledaliy.network.interceptor.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class TokenSignRetrofit private constructor() : HttpImpl {
    override fun getRetrofit(): Retrofit? {
        return retrofit
    }

    class Build {
        private fun create() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okBuild = OkHttpClient.Builder()
            okBuild.addInterceptor(TokenInterceptor())
            okBuild.addInterceptor(SignInterceptor())
            okBuild.addInterceptor(ResponsInterceptor())
            okBuild.addInterceptor(interceptor)
            okBuild.connectTimeout((30 * 1000).toLong(), TimeUnit.MILLISECONDS)
            okBuild.readTimeout((30 * 1000).toLong(), TimeUnit.MILLISECONDS)
            okBuild.writeTimeout((30 * 1000).toLong(), TimeUnit.MILLISECONDS)
            val builder = Retrofit.Builder()
            builder.baseUrl(Api.BaseUrl)
            builder.client(okBuild.build())
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            builder.addConverterFactory(GsonConverterFactory.create())
            retrofit = builder.build()
        }

        fun build(): TokenSignRetrofit {
            return TokenSignRetrofit()
        }

        init {
            create()
        }
    }

    companion object {
        private var retrofit: Retrofit? = null
    }
}