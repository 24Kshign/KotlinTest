package cn.share.kotlintest.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.share.kotlintest.addAll
import cn.share.kotlintest.clear
import cn.share.kotlintest.get
import cn.share.kotlintest.size
import java.util.*

/**
 * Created by jack on 2018/5/21
 */
abstract class BaseCommonAdapter<DATA> : RecyclerView.Adapter<BaseCommonViewHolder> {

    private var mDataList: ArrayList<DATA>? = null
    private var mContext: Context

    constructor(context: Context) {
        mContext = context
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCommonViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(getLayoutRes(), parent, false)
        return BaseCommonViewHolder(itemView)
    }

    abstract fun getLayoutRes(): Int

    override fun onBindViewHolder(holder: BaseCommonViewHolder, position: Int) {
        convert(holder, mDataList.get(position), position, null)
    }

    override fun onBindViewHolder(holder: BaseCommonViewHolder, position: Int, payloads: List<Any>) {
        convert(holder, mDataList.get(position), position, payloads)
    }

    /**
     * 让子类去实现数据绑定
     */
    protected abstract fun convert(holder: BaseCommonViewHolder, data: DATA?, position: Int, payloads: List<Any>?)

    /**
     * 设置数据源
     *
     * @param dataList 数据
     */
    fun setDataList(dataList: ArrayList<DATA>) {
        if (null == mDataList) {
            mDataList = ArrayList()
        }
        mDataList.clear()
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    /**
     * 更新局部数据
     *
     * @param position item的位置
     * @param data     item的数据
     */
    fun updateItem(position: Int, data: DATA) {
        mDataList?.set(position, data)
        //更新单个item，设置第二个数据不为null解决刷新闪屏问题
        notifyItemChanged(position, "payload")
    }
}