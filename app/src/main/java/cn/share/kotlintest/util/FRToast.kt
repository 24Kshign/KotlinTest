package cn.share.kotlintest.util

import android.widget.Toast
import cn.share.kotlintest.app.App

/**
 * Created by jack on 2018/5/23
 */

object FRToast {
    private var oldMsg: String? = null
    private var mToast: Toast? = null
    private var lastTime: Long = 0
    private var nextTime: Long = 0

    fun showToast(s: String) {
        if (mToast == null) {
            mToast = Toast.makeText(App.instance, s, Toast.LENGTH_SHORT)
            mToast?.show()
            lastTime = System.currentTimeMillis()
        } else {
            nextTime = System.currentTimeMillis()
            if (s == oldMsg) {
                if (nextTime - lastTime > Toast.LENGTH_SHORT) {
                    mToast!!.show()
                }
            } else {
                oldMsg = s
                mToast?.setText(s)
                mToast?.show()
            }
        }
        lastTime = nextTime
    }

    fun showToast(textRes: Int) {
        showToast(App.instance.resources.getString(textRes))
    }
}