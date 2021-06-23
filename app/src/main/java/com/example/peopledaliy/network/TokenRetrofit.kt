package com.example.peopledaliy.network

import com.example.peopledaliy.network.interceptor.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class TokenRetrofit private constructor() : HttpImpl {
    override fun getRetrofit(): Retrofit? {
        return retrofit
    }

    class Build {
        private fun createRetrofit() {
            val okBuilder = OkHttpClient.Builder()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okBuilder.addInterceptor(TokenInterceptor())
            okBuilder.addInterceptor(interceptor)
            okBuilder.connectTimeout((30 * 1000).toLong(), TimeUnit.MILLISECONDS)
            okBuilder.writeTimeout((30 * 1000).toLong(), TimeUnit.MILLISECONDS)
            okBuilder.readTimeout((30 * 1000).toLong(), TimeUnit.MILLISECONDS)
            val builder = Retrofit.Builder()
            builder.client(okBuilder.build())
            builder.baseUrl(Api.BaseUrl)
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            builder.addConverterFactory(GsonConverterFactory.create())
            retrofit = builder.build()
        }

        fun build(): TokenRetrofit {
            return TokenRetrofit()
        }

        init {
            createRetrofit()
        }
    }

    companion object {
        private var retrofit: Retrofit? = null
    }
}
