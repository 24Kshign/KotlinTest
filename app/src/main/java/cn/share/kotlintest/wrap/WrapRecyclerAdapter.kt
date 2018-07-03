package cn.share.kotlintest.wrap

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup

/**
 * Created by jack on 2018/5/28
 */

class WrapRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

    private var mHeaderArray = SparseArray<View>()
    private var mFooterArray = SparseArray<View>()

    private var BASE_HEADER_KEY = 1000000
    private var BASE_FOOTER_KEY = 2000000

    constructor(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        mAdapter = adapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //根据viewType来区分是头部，底部还是列表
        if (mHeaderArray.indexOfKey(viewType) >= 0) {
            //头部
            return createHeaderAndFooterViewHolder(mHeaderArray.get(viewType))
        } else if (mFooterArray.indexOfKey(viewType) >= 0) {
            //底部
            return createHeaderAndFooterViewHolder(mFooterArray.get(viewType))
        }
        //列表
        return mAdapter.onCreateViewHolder(parent, viewType)
    }

    private fun createHeaderAndFooterViewHolder(view: View): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(view) {
        }
    }

    override fun getItemCount(): Int {
        val totalCount = mAdapter.itemCount + mHeaderArray.size() + mFooterArray.size()
        return totalCount
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //只需要处理中间列表
        val numberHeaders = mHeaderArray.size()
        if (position < numberHeaders) {
            //头部
            return
        }
        val adjPosition = position - numberHeaders
        val adapterCount = mAdapter.itemCount
        if (adjPosition < adapterCount) {
            //中间
            mAdapter.onBindViewHolder(holder, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val numberHeaders = mHeaderArray.size()
        if (position < numberHeaders) {
            //头部
            return mHeaderArray.keyAt(position)
        }
        val adjPosition = position - numberHeaders
        val adapterCount = mAdapter.itemCount
        if (adjPosition < adapterCount) {
            //中间
            return mAdapter.getItemViewType(adjPosition)
        }
        //底部
        return mFooterArray.keyAt(adjPosition - adapterCount)
    }

    fun addHeader(view: View) {
        if (mHeaderArray.indexOfValue(view) == -1) {
            //集合没有，需要添加
            mHeaderArray.put(BASE_HEADER_KEY++, view)
        }
    }

    fun addFooter(view: View) {
        if (mFooterArray.indexOfValue(view) == -1) {
            //集合没有，需要添加
            mFooterArray.put(BASE_FOOTER_KEY++, view)
        }
    }

    fun removeHeader(view: View) {
        if (mHeaderArray.indexOfValue(view) >= 0) {
            mHeaderArray.removeAt(mHeaderArray.indexOfValue(view))
        }
    }

    fun removeFooter(view: View) {
        if (mFooterArray.indexOfValue(view) >= 0) {
            mFooterArray.removeAt(mFooterArray.indexOfValue(view))
        }
    }
}