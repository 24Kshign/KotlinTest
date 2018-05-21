package cn.share.kotlintest

/**
 * Created by jack on 2018/5/21
 */


val Collection<*>?.size: Int get() = this?.size ?: 0

fun Collection<*>?.isNullOrEmpty(): Boolean = this == null || isEmpty()

fun <E> Collection<E>?.contains(element: E): Boolean = this != null && contains(element)

fun <DATA> ArrayList<DATA>?.get(position: Int): DATA? {
    return this?.get(position)
}

fun <E> MutableCollection<E>?.clear(): Unit {
    this?.let { clear() }
}

fun <E> MutableCollection<E>?.removeAll(): Unit {
    this?.let { removeAll() }
}

fun <E> MutableCollection<E>?.addAll(elements: Collection<E>): Unit {
    this?.let { addAll(elements) }
}