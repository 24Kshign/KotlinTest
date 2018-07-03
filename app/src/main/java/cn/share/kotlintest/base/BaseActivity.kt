package cn.share.kotlintest.base

import android.os.Bundle
import android.support.v4.app.FragmentActivity

/**
 * Created by jack on 2018/5/25
 */
abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (bindLayoutRes() == 0) {
            throw IllegalArgumentException("the xml should not be null")
        }
        setContentView(bindLayoutRes())
    }

    abstract fun bindLayoutRes(): Int

}