package cn.share.kotlintest.dialog

import android.app.Dialog
import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import cn.share.kotlintest.R


/**
 * Created by jack on 2018/5/24
 */
class FRDialog(context: Context, themeId: Int) : Dialog(context, themeId) {

    var dialogViewHelper: FRDialogViewHelper? = null

    fun setViewVisibleOrGone(@IdRes id: Int, isVisible: Boolean) {
        dialogViewHelper?.setViewVisibleOrGone(id, isVisible)
    }

    fun attach(baseBuilder: FRBaseDialogBuilder) {
        dialogViewHelper = baseBuilder.mContentView?.let { FRDialogViewHelper(it) }
        setContentView(dialogViewHelper!!.mContentView)
        dialogViewHelper?.mDialog = this
        setCanceledOnTouchOutside(baseBuilder.mCancelableOutside)
        setCancelable(baseBuilder.mCancelable)
        if (null != baseBuilder.mOnCancelListener) {
            setOnCancelListener(baseBuilder.mOnCancelListener)
        }
        if (null != baseBuilder.mOnDismissListener) {
            setOnDismissListener(baseBuilder.mOnDismissListener)
        }
        if (null != baseBuilder.mOnKeyListener) {
            setOnKeyListener(baseBuilder.mOnKeyListener)
        }
        if (null != window) {
            window.setGravity(baseBuilder.mGravity)
            if (baseBuilder.mAnimation != 0) {
                window.setWindowAnimations(baseBuilder.mAnimation)
            }
            val lp = window.getAttributes()
            lp.width = (baseBuilder.mContext.resources.displayMetrics.widthPixels * baseBuilder.mWidthOffset).toInt()
            lp.height = LinearLayout.LayoutParams.WRAP_CONTENT
            window.attributes = lp
        }
    }

    class CommonBuilder : FRBaseDialogBuilder {

        constructor(context: Context, themeId: Int) : super(context, themeId)
        constructor(context: Context) : super(context, R.style.dialog)

        fun setContentView(@LayoutRes layoutRes: Int): CommonBuilder {
            if (layoutRes != 0) {
                setContentView(LayoutInflater.from(mContext).inflate(layoutRes, null))
            }
            return this
        }

        fun setContentView(view: View): CommonBuilder {
            mContentView = view
            return this
        }
    }
}