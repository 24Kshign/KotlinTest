package cn.share.kotlintest.wrap

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

/**
 * Created by jack on 2018/5/28
 */
class WrapRecyclerView : RecyclerView {

    private lateinit var mAdapter: WrapRecyclerAdapter

    private var mAdapterDataObserver: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            mAdapter.notifyDataSetChanged()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            mAdapter.notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            mAdapter.notifyItemRangeChanged(positionStart, itemCount, payload)
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            mAdapter.notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            mAdapter.notifyItemMoved(fromPosition, toPosition)
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            mAdapter.notifyItemRangeRemoved(positionStart, itemCount)
        }
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        //删除的问题是因为列表的Adapter改变了，但是WrapRecyclerAdapter并没有修改，所以需要通过观察者模式来相关联
        if (adapter is WrapRecyclerAdapter) {
            mAdapter = adapter
        } else {
            mAdapter = WrapRecyclerAdapter(adapter)
            mAdapter.registerAdapterDataObserver(mAdapterDataObserver)
        }
        super.setAdapter(mAdapter)
    }

    fun addHeader(view: View) {
        mAdapter.addHeader(view)
    }

    fun addFooter(view: View) {
        mAdapter.addFooter(view)
    }

    fun removeHeader(view: View) {
        mAdapter.removeHeader(view)
    }

    fun removeFooter(view: View) {
        mAdapter.removeFooter(view)
    }
}
