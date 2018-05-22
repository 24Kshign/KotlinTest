package cn.share.kotlintest.util

import cn.share.kotlintest.FRString.isNullable

/**
 * Created by jack on 2018/5/22
 */


fun Int.isStatusSuccess(): Boolean = this == 1

fun String.isStatusSuccess(): Boolean = !this.isNullable() && this == "1"