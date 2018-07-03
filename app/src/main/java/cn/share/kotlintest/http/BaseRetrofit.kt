package cn.share.kotlintest.http

import cn.share.kotlintest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by jack on 2018/6/8
 */
open class BaseRetrofit {

    lateinit var mRetrofit: Retrofit
    private val DEFAULT_TIME = 20    //默认超时时间
    private val DEFAULT_RETRY = 3

    fun init() {
        val builder = OkHttpClient().newBuilder()

        builder.addInterceptor(HttpLoggingInterceptor().setLevel(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        ))
        builder.readTimeout(getDefaultTimeOutTime().toLong(), TimeUnit.SECONDS)
        builder.connectTimeout(getDefaultTimeOutTime().toLong(), TimeUnit.SECONDS)

        val okHttpClient: OkHttpClient = builder.build()

        mRetrofit = Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    open fun getBaseUrl(): String {
        return ""
    }

    fun getDefaultTimeOutTime(): Int {
        return DEFAULT_TIME
    }

    fun getDefaultRetryTime(): Int {
        return DEFAULT_RETRY
    }
}