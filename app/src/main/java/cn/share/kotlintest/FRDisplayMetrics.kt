package cn.share.kotlintest

import android.util.TypedValue
import cn.share.kotlintest.app.App

/**
 * Created by jack on 2018/5/21
 */

fun Int.dp(): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), App.instance.resources.displayMetrics).toInt()

fun Int.sp(): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), App.instance.resources.displayMetrics).toInt()

fun Int.px(): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, this.toFloat(), App.instance.resources.displayMetrics).toInt()

fun String?.isNullable() = this == null || length == 0

fun Any?.string(): String = toString()