package cn.share.kotlintest

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cn.share.kotlintest.adapter.MainAdapterRecycler
import cn.share.kotlintest.bean.MainBean
import cn.share.kotlintest.recyclerview.OnRecyclerItemClickListener
import cn.share.kotlintest.util.FRStartActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    companion object {
        fun start(context: Context) {
            FRStartActivity.start(context, MainActivity::class.java)
        }
    }

    private lateinit var mAdapter: MainAdapterRecycler
    private lateinit var dataList: ArrayList<MainBean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = MainAdapterRecycler(this)
        am_recyclerview.layoutManager = LinearLayoutManager(this)
        am_recyclerview.adapter = mAdapter

        initData()
        initListener()
    }

    private fun initListener() {
        mAdapter.setOnItemClickListener(object : OnRecyclerItemClickListener.OnItemClickListener {
            override fun click(view: View, position: Int) {
                mAdapter.removeItem(position)
            }
        })

        am_append_item.setOnClickListener {
            mAdapter.appendItem(MainBean("append insert item", R.mipmap.ic_launcher_round))
            am_recyclerview.scrollToPosition(mAdapter.itemCount - 1)
        }

        am_front_item.setOnClickListener {
            mAdapter.frontItem(MainBean("front insert item", R.mipmap.ic_launcher_round))
            am_recyclerview.scrollToPosition(0)
        }

        am_remove_item.setOnClickListener {
            mAdapter.removeItem(mAdapter.itemCount / 2)
        }

        am_front_list.setOnClickListener {
            mAdapter.frontList(getFrontLit())
            am_recyclerview.scrollToPosition(0)
        }
    }

    private fun getFrontLit(): ArrayList<MainBean> {
        val list: ArrayList<MainBean> = ArrayList()
        var mainBean: MainBean
        for (i in 1..4) {
            mainBean = MainBean("front insert list $i", R.mipmap.ic_launcher_round)
            list.add(mainBean)
        }
        return list
    }

    private fun initData() {
        dataList = ArrayList()
        var mainBean: MainBean
        for (i in 1..15) {
            mainBean = MainBean("24K纯帅$i", R.mipmap.ic_launcher_round)
            dataList.add(mainBean)
        }
        mAdapter.setDataList(dataList)
    }
}