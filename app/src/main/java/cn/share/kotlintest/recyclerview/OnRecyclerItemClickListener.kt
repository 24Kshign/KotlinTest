package cn.share.kotlintest.recyclerview

import android.view.View

/**
 * Created by jack on 2018/5/23
 */

class OnRecyclerItemClickListener{

    interface OnItemClickListener {
        fun click(view: View,position:Int)
    }

    interface OnLongItemClickListener{
        fun click(view: View,position:Int):Boolean
    }
}
