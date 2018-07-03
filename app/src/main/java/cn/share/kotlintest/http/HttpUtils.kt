package cn.share.kotlintest.http

/**
 * Created by jack on 2018/6/8
 */
class HttpUtils : BaseRetrofit() {

    lateinit var mService: FRApi
    private lateinit var mBaseUrl: String

    private object Holder {
        val INSTANCE = HttpUtils()
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }

    fun baseUrl(baseUrl: String): HttpUtils {
        mBaseUrl = baseUrl
        return this
    }

    override fun getBaseUrl(): String {
        return mBaseUrl
    }
}