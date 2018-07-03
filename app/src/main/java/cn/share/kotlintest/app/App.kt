package cn.share.kotlintest.app

import android.app.Application

/**
 * Created by jack on 2018/5/21
 */

class App : Application() {

    companion object {
        @JvmStatic lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}