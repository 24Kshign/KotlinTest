package cn.share.kotlintest

/**
 * Created by jack on 2018/5/19
 */

object FRString {
    fun isNullable(vararg strings: String): Boolean {
        return strings.any { it.isNullable() }
    }
}