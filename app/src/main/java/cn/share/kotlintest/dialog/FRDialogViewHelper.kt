package cn.share.kotlintest.dialog

import android.content.res.ColorStateList
import android.support.annotation.ColorInt
import android.support.annotation.IdRes
import android.util.SparseArray
import android.view.View
import android.widget.EditText
import android.widget.TextView
import cn.share.kotlintest.FRString.isNullable
import java.lang.ref.WeakReference

/**
 * Created by jack on 2018/5/24
 */
class FRDialogViewHelper(view: View) {

    var mContentView: View = view
    var mDialog: FRDialog? = null

    private val mViews: SparseArray<WeakReference<View>> = SparseArray()

    fun setDialog(dialog: FRDialog) {
        mDialog = dialog
    }

    fun <VIEW : View> getView(@IdRes idRes: Int): VIEW {
        //防止多次findViewById
        val viewWeakReference = mViews.get(idRes)
        var view: View? = null
        if (null != viewWeakReference) {
            view = viewWeakReference.get()
        }
        if (null == view) {
            view = mContentView.findViewById(idRes)
            if (null != view) {
                mViews.put(idRes, WeakReference(view))
            }
        }
        return view as VIEW
    }

    fun setText(@IdRes idRes: Int, charSequence: CharSequence?) {
        val textView: TextView? = getView(idRes)
        if (charSequence.toString().isNullable()) {
            textView?.text = charSequence
        }
    }

    fun setTextColor(@IdRes idRes: Int, color: ColorStateList) {
        val textView: TextView? = getView(idRes)
        textView?.setTextColor(color)
    }

    fun setTextColor(@IdRes idRes: Int, @ColorInt color: Int) {
        val textView: TextView? = getView(idRes)
        textView?.setTextColor(color)
    }

    fun setOnDialogClickListener(@IdRes idRes: Int, dialogClickListener: FRDialogClickListener?) {
        val view: View? = getView(idRes)
        if (null != dialogClickListener) {
            view?.setOnClickListener {
                val dismiss = dialogClickListener.onDialogClick(view)
                if (dismiss) {
                    mDialog?.dismiss()
                }
            }
        }
    }

    fun setViewVisibleOrGone(@IdRes idRes: Int, isVisible: Boolean) {
        val view: View? = getView(idRes)
        view?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun addTextChangedListener(@IdRes idRes: Int, frDialogTextChangeListener: FRDialogTextChangeListener?) {
        val editText: EditText? = getView(idRes)
        if (null != frDialogTextChangeListener) {
            editText?.addTextChangedListener(frDialogTextChangeListener)
        }
    }
}