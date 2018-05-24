package cn.share.kotlintest.dialog

import android.view.View

/**
 * Created by jack on 2018/5/24
 */
interface FRDialogClickListener{

    //返回true表示dialog会dismiss，否则不会
    fun onDialogClick(view: View):Boolean
}