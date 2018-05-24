package cn.share.kotlintest.dialog

import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.support.annotation.ColorInt
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.share.kotlintest.FRString.isNullable
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
            val lp = window.attributes
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

    class MaterialDesignBuilder : FRBaseDialogBuilder {

        private var mNegativeContent: String? = null
        private var mNegativeListener: FRDialogClickListener? = null

        constructor(context: Context, themeId: Int) : super(context, themeId)
        constructor(context: Context) : this(context, R.style.md_dialog)

        init {
            mContentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_material, null)
        }

        //设置MD效果dialog的头部
        fun setTitle(charSequence: CharSequence): MaterialDesignBuilder {
            mTextArray.put(R.id.dialog_material_tv_title, charSequence)
            return this
        }

        //设置MD效果dialog内容
        fun setMessage(charSequence: CharSequence): MaterialDesignBuilder {
            mTextArray.put(R.id.dialog_material_tv_content, charSequence)
            return this
        }

        fun setNegativeContentAndListener(charSequence: CharSequence, frDialogClickListener: FRDialogClickListener?): MaterialDesignBuilder {
            mNegativeContent = charSequence.toString()
            mNegativeListener = frDialogClickListener
            mClickListenerArray.put(R.id.dialog_material_tv_cancel, frDialogClickListener)
            mTextArray.put(R.id.dialog_material_tv_cancel, charSequence)
            return this
        }

        fun setPositiveContentAndListener(charSequence: CharSequence, frDialogClickListener: FRDialogClickListener?): MaterialDesignBuilder {
            mClickListenerArray.put(R.id.dialog_material_tv_confirm, frDialogClickListener)
            mTextArray.put(R.id.dialog_material_tv_confirm, charSequence)
            return this
        }

        fun setNegativeTextColor(@ColorInt color: Int): MaterialDesignBuilder {
            mTextColorArray.put(R.id.dialog_material_tv_cancel, color)
            return this
        }

        fun setNegativeTextColor(color: ColorStateList): MaterialDesignBuilder {
            mTextColorStateListArray.put(R.id.dialog_material_tv_cancel, color)
            return this
        }

        fun setPositiveTextColor(@ColorInt color: Int): MaterialDesignBuilder {
            mTextColorArray.put(R.id.dialog_material_tv_confirm, color)
            return this
        }

        fun setPositiveTextColor(color: ColorStateList): MaterialDesignBuilder {
            mTextColorStateListArray.put(R.id.dialog_material_tv_confirm, color)
            return this
        }

        override fun attachView(): Boolean {
            if (!mNegativeContent.isNullable() && null == mNegativeListener) {
                getView<TextView>(R.id.dialog_material_tv_cancel)?.setOnClickListener {
                    mDialog?.dismiss()
                }
            }
            return super.attachView()
        }
    }
}