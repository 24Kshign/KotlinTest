package cn.share.kotlintest.util

import android.widget.Toast
import cn.share.kotlintest.app.App
import cn.share.kotlintest.isNullable


/**
 * Created by jack on 2018/5/22
 */


fun Int.isStatusSuccess(): Boolean = this == 1

fun String?.isStatusSuccess(): Boolean = !this.isNullable() && this == "1"

fun String.showToast(): Unit = Toast.makeText(App.instance, this, if (this.length > 5) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()