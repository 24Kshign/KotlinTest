package cn.share.kotlintest.util

import android.os.Bundle
import android.os.Parcelable

/**
 * Created by jack on 2018/5/25
 */

class FRBundleHelper(val bundle: Bundle) {

    constructor() : this(Bundle())

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // put value

    inline infix fun String?.to(value: Boolean) {
        bundle.putBoolean(this, value)
    }

    inline infix fun String?.to(value: Char) {
        bundle.putChar(this, value)
    }

    inline infix fun String?.to(value: Byte) {
        bundle.putByte(this, value)
    }

    inline infix fun String?.to(value: Short) {
        bundle.putShort(this, value)
    }

    inline infix fun String?.to(value: Int) {
        bundle.putInt(this, value)
    }

    inline infix fun String?.to(value: Long) {
        bundle.putLong(this, value)
    }

    inline infix fun String?.to(value: Float) {
        bundle.putFloat(this, value)
    }

    inline infix fun String?.to(value: Double) {
        bundle.putDouble(this, value)
    }

    inline infix fun String?.to(value: String?) {
        bundle.putString(this, value)
    }

    inline infix fun String?.to(value: CharSequence?) {
        bundle.putCharSequence(this, value)
    }

    inline infix fun String?.to(value: Parcelable?) {
        bundle.putParcelable(this, value)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // put Array

    inline infix fun String?.to(value: BooleanArray?) {
        bundle.putBooleanArray(this, value)
    }

    inline infix fun String?.to(value: CharArray?) {
        bundle.putCharArray(this, value)
    }

    inline infix fun String?.to(value: ByteArray?) {
        bundle.putByteArray(this, value)
    }

    inline infix fun String?.to(value: ShortArray?) {
        bundle.putShortArray(this, value)
    }

    inline infix fun String?.to(value: IntArray?) {
        bundle.putIntArray(this, value)
    }

    inline infix fun String?.to(value: LongArray?) {
        bundle.putLongArray(this, value)
    }

    inline infix fun String?.to(value: FloatArray?) {
        bundle.putFloatArray(this, value)
    }

    inline infix fun String?.to(value: DoubleArray?) {
        bundle.putDoubleArray(this, value)
    }

    inline infix fun String?.to(value: Array<String?>?) {
        bundle.putStringArray(this, value)
    }

    inline infix fun String?.to(value: Array<CharSequence?>?) {
        bundle.putCharSequenceArray(this, value)
    }

    inline infix fun String?.to(value: Array<Parcelable?>?) {
        bundle.putParcelableArray(this, value)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // put ArrayList

    inline infix fun String?.toIntegerArrayList(value: ArrayList<Int?>?) {
        bundle.putIntegerArrayList(this, value)
    }

    inline infix fun String?.toStringArrayList(value: ArrayList<String?>?) {
        bundle.putStringArrayList(this, value)
    }

    inline infix fun String?.toCharSequenceArrayList(value: ArrayList<CharSequence?>?) {
        bundle.putCharSequenceArrayList(this, value)
    }

    inline infix fun String?.toParcelableArrayList(value: ArrayList<Parcelable?>?) {
        bundle.putParcelableArrayList(this, value)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // get value

    inline fun String?.boolean(): Boolean = bundle.getBoolean(this)

    inline fun String?.char(): Char = bundle.getChar(this)

    inline fun String?.byte(): Byte = bundle.getByte(this)

    inline fun String?.short(): Short = bundle.getShort(this)

    inline fun String?.int(): Int = bundle.getInt(this)

    inline fun String?.long(): Long = bundle.getLong(this)

    inline fun String?.float(): Float = bundle.getFloat(this)

    inline fun String?.double(): Double = bundle.getDouble(this)

    inline fun String?.string(): String? = bundle.getString(this)

    inline fun String?.charSequence(): CharSequence? = bundle.getCharSequence(this)

    inline fun String?.parcelable(): Parcelable? = bundle.getParcelable(this)

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // get Array

    inline fun String?.booleanArray(): BooleanArray? = bundle.getBooleanArray(this)

    inline fun String?.charArray(): CharArray? = bundle.getCharArray(this)

    inline fun String?.byteArray(): ByteArray? = bundle.getByteArray(this)

    inline fun String?.shortArray(): ShortArray? = bundle.getShortArray(this)

    inline fun String?.intArray(): IntArray? = bundle.getIntArray(this)

    inline fun String?.longArray(): LongArray? = bundle.getLongArray(this)

    inline fun String?.floatArray(): FloatArray? = bundle.getFloatArray(this)

    inline fun String?.doubleArray(): DoubleArray? = bundle.getDoubleArray(this)

    inline fun String?.stringArray(): Array<String?>? = bundle.getStringArray(this)

    inline fun String?.charSequenceArray(): Array<CharSequence?>? = bundle.getCharSequenceArray(this)

    inline fun String?.parcelableArray(): Array<Parcelable?>? = bundle.getParcelableArray(this)

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // get ArrayList

    inline fun String?.integerArrayList(): ArrayList<Int?>? = bundle.getIntegerArrayList(this)

    inline fun String?.stringArrayList(): ArrayList<String?>? = bundle.getStringArrayList(this)

    inline fun String?.charSequenceArrayList(): ArrayList<CharSequence?>? = bundle.getCharSequenceArrayList(this)

    inline fun String?.parcelableArrayList(): ArrayList<Parcelable?>? = bundle.getParcelableArrayList(this)
}
