package cn.share.kotlintest.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.share.kotlintest.addAll
import cn.share.kotlintest.clear
import cn.share.kotlintest.get
import cn.share.kotlintest.recyclerview.OnRecyclerItemClickListener.OnItemClickListener
import cn.share.kotlintest.recyclerview.OnRecyclerItemClickListener.OnLongItemClickListener
import cn.share.kotlintest.size
import java.util.*

/**
 * Created by jack on 2018/5/21
 */
abstract class BaseRecyclerCommonAdapter<DATA> : RecyclerView.Adapter<BaseRecyclerCommonViewHolder> {

    private var mDataList: ArrayList<DATA>? = null
    private var mContext: Context
    private var onRecyclerItemClickListener: OnItemClickListener? = null
    private var onLongRecyclerItemClickListener: OnLongItemClickListener? = null
    private var mRecyclerMultiType: RecyclerMultiType<DATA>? = null

    constructor(context: Context) {
        mContext = context
    }

    constructor(context: Context, recyclerMultiType: RecyclerMultiType<DATA>) : this(context) {
        mRecyclerMultiType = recyclerMultiType
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerCommonViewHolder {
        val mLayoutRes = if (null == mRecyclerMultiType) getLayoutRes() else viewType
        if (mLayoutRes == 0) {
            throw IllegalArgumentException("the layoutRes of adapter should not be null")
        }
        val itemView = LayoutInflater.from(mContext).inflate(mLayoutRes, parent, false)
        return BaseRecyclerCommonViewHolder(itemView)
    }

    open fun getLayoutRes(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: BaseRecyclerCommonViewHolder, position: Int) {
        convert(holder, mDataList.get(position), position, null)
        setOnClickListener(holder)
    }

    override fun onBindViewHolder(holder: BaseRecyclerCommonViewHolder, position: Int, payloads: List<Any>) {
        convert(holder, mDataList.get(position), position, payloads)
        setOnClickListener(holder)
    }

    private fun setOnClickListener(holder: BaseRecyclerCommonViewHolder) {
        holder.itemView.setOnClickListener { view ->
            onRecyclerItemClickListener?.click(view, holder.layoutPosition)
        }
        holder.itemView.setOnLongClickListener { view ->
            onLongRecyclerItemClickListener?.click(view, holder.layoutPosition)!!
        }
    }

    /**
     * 让子类去实现数据绑定
     */
    protected abstract fun convert(holder: BaseRecyclerCommonViewHolder, data: DATA?, position: Int, payloads: List<Any>?)

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

    fun setOnItemClickListener(onRecyclerItemClickListener: OnItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener
    }

    fun setOnLongItemClickListener(onLongRecyclerItemClickListener: OnLongItemClickListener) {
        this.onLongRecyclerItemClickListener = onLongRecyclerItemClickListener
    }
}