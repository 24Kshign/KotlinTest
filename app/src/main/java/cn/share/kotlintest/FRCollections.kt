package cn.share.kotlintest

/**
 * Created by jack on 2018/5/21
 */


val Collection<*>?.size: Int get() = this?.size ?: 0

fun Collection<*>?.isNullOrEmpty(): Boolean = this == null || isEmpty()

fun <E> Collection<E>?.contains(element: E): Boolean = this != null && contains(element)

fun <DATA> List<DATA>?.get(position: Int): DATA? {
    return this?.get(position)
}

fun <E> MutableList<E>?.clear() {
    this?.let { clear() }
}

fun <E> MutableList<E>?.removeAll() {
    this?.let { removeAll(this) }
}

fun <E> MutableList<E>?.addAll(elements: Collection<E>) {
    this?.let { addAll(elements) }
}

fun <E> MutableList<E>?.addAll(position: Int, elements: Collection<E>) {
    this?.let { addAll(position, elements) }
}

fun <E> MutableList<E>?.add(element: E) {
    this?.let { add(element) }
}

fun <E> MutableList<E>?.add(position: Int, element: E) {
    this?.let { add(position, element) }
}

fun <E> MutableList<E>?.set(position: Int, element: E) {
    this?.let { set(position, element) }
}

fun <E> MutableList<E>?.removeAt(position: Int) {
    this?.let { removeAt(position) }
}