package cn.share.kotlintest

/**
 * Created by jack on 2018/5/19
 */
object FRString {

    fun String?.isNullable(): Boolean = this == null || length == 0

    fun Any?.string(): String {
        return this.toString()
    }

    fun isNullable(vararg strings: String): Boolean {
        return strings.any { it.isNullable() }
    }
}