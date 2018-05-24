package cn.share.kotlintest.dialog

import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.support.annotation.IdRes
import android.util.SparseArray
import android.util.SparseIntArray
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import cn.share.kotlintest.R

/**
 * Created by jack on 2018/5/24
 */

open class FRBaseDialogBuilder(context: Context, themeId: Int) {

    internal var mContext: Context = context
    private var mThemeId: Int = themeId
    internal var mWidthOffset: Double = 0.9  //宽度占整个屏幕宽度的比例
    internal var mHeight: Int = ViewGroup.LayoutParams.WRAP_CONTENT
    internal var mAnimation: Int = 0
    internal var mCancelable: Boolean = true
    internal var mCancelableOutside: Boolean = true
    internal var mGravity: Int = Gravity.CENTER
    internal var mOnDismissListener: DialogInterface.OnDismissListener? = null
    internal var mOnCancelListener: DialogInterface.OnCancelListener? = null
    internal var mOnKeyListener: DialogInterface.OnKeyListener? = null

    //dialog布局上的文案
    internal var mTextArray = SparseArray<CharSequence>()
    internal var mTextColorArray = SparseIntArray()
    internal var mTextColorStateListArray = SparseArray<ColorStateList>()

    //dialog上控件的点击事件和EditText的输入监听事件
    internal var mClickListenerArray: SparseArray<FRDialogClickListener> = SparseArray()
    internal var mTextChangeListenerArray: SparseArray<FRDialogTextChangeListener> = SparseArray()

    private var mDialogViewHelper: FRDialogViewHelper? = null
    internal var mDialog: FRDialog? = null
    internal var mContentView: View? = null

    fun fullWidth(): FRBaseDialogBuilder {
        mWidthOffset = 1.0
        return this
    }

    fun widthOffset(width: Double): FRBaseDialogBuilder {
        mWidthOffset = width
        return this
    }

    fun height(height: Int): FRBaseDialogBuilder {
        mHeight = height
        return this
    }

    fun fromBottom(): FRBaseDialogBuilder {
        mAnimation = R.style.dialog_from_bottom_anim
        mGravity = Gravity.BOTTOM
        return this
    }

    fun defaultAnimation(): FRBaseDialogBuilder {
        mAnimation = R.style.dialog_scale_anim
        return this
    }

    fun setAnimation(animation: Int): FRBaseDialogBuilder {
        mAnimation = animation
        return this
    }

    fun setCancelable(cancelable: Boolean): FRBaseDialogBuilder {
        mCancelable = cancelable
        return this
    }

    fun setOnClickListener(@IdRes id: Int, onFRDialogClickListener: FRDialogClickListener): FRBaseDialogBuilder {
        mClickListenerArray.put(id, onFRDialogClickListener)
        return this
    }

    fun setCancelableOutside(cancelableOutside: Boolean): FRBaseDialogBuilder {
        mCancelableOutside = cancelableOutside
        return this
    }

    fun setOnCancelListener(onCancelListener: DialogInterface.OnCancelListener): FRBaseDialogBuilder {
        mOnCancelListener = onCancelListener
        return this
    }

    fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener): FRBaseDialogBuilder {
        mOnDismissListener = onDismissListener
        return this
    }

    fun setOnKeyListener(onKeyListener: DialogInterface.OnKeyListener): FRBaseDialogBuilder {
        mOnKeyListener = onKeyListener
        return this
    }

    fun setText(@IdRes id: Int, charSequence: CharSequence): FRBaseDialogBuilder {
        mTextArray.put(id, charSequence)
        return this
    }

    fun setTextColor(@IdRes id: Int, color: Int): FRBaseDialogBuilder {
        mTextColorArray.put(id, color)
        return this
    }

    open fun <VIEW : View> getView(@IdRes idRes: Int): VIEW? {
        return mDialogViewHelper?.getView(idRes)
    }

    fun create(): FRDialog {
        if (null == mDialog) {
            mDialog = FRDialog(mContext, mThemeId)
            mDialog?.attach(this)
            mDialogViewHelper = mDialog?.dialogViewHelper
            attachView()
        }
        return mDialog as FRDialog
    }

    open fun attachView(): Boolean {
        for (i in 0 until mTextArray.size()) {
            mDialogViewHelper?.setText(mTextArray.keyAt(i), mTextArray.valueAt(i))
        }
        for (i in 0 until mClickListenerArray.size()) {
            mDialogViewHelper?.setOnDialogClickListener(mClickListenerArray.keyAt(i), mClickListenerArray.valueAt(i))
        }
        for (i in 0 until mTextColorArray.size()) {
            mDialogViewHelper?.setTextColor(mTextColorArray.keyAt(i), mTextColorArray.valueAt(i))
        }
        for (i in 0 until mTextColorStateListArray.size()) {
            mDialogViewHelper?.setTextColor(mTextColorStateListArray.keyAt(i), mTextColorStateListArray.valueAt(i))
        }
        for (i in 0 until mTextChangeListenerArray.size()) {
            mDialogViewHelper?.addTextChangedListener(mTextChangeListenerArray.keyAt(i), mTextChangeListenerArray.valueAt(i))
        }
        return true
    }

    fun show(): FRDialog {
        if (null != mDialog) {
            mDialog?.show()
        } else {
            create().show()
        }
        return mDialog as FRDialog
    }

}