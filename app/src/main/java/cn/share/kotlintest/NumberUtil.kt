package cn.share.kotlintest

/**
 * Created by jack on 2018/5/19
 */
object NumberUtil {

    fun add(a: Int, b: Int): Int = a + b;

    fun sub(a: Int, b: Int): Int = a - b;

    fun mul(a: Int, b: Int): Int = a * b;

    fun div(a: Int, b: Int): Int {
        if (b == 0) {
            throw IllegalArgumentException("The Divisor should not be 0")
        }
        return a / b;
    }
}