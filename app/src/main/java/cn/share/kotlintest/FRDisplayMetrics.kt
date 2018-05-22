package cn.share.kotlintest

import android.util.TypedValue

/**
 * Created by jack on 2018/5/21
 */

inline fun Int.dp(): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, this.toFloat(), App.instance.resources.displayMetrics).toInt()