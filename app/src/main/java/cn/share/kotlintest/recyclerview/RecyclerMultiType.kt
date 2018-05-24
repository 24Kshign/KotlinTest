package cn.share.kotlintest.recyclerview

/**
 * Created by jack on 2018/5/23
 */

interface RecyclerMultiType<DATA> {

    fun getLayoutRes(data: DATA): Int

}