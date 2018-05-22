package cn.share.kotlintest

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import cn.share.kotlintest.adapter.MainAdapter
import cn.share.kotlintest.bean.MainBean
import cn.share.kotlintest.util.FRStartActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    companion object {
        fun start(context: Context) {
            FRStartActivity.start(context, MainActivity::class.java)
        }
    }

    private lateinit var mAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = MainAdapter(this)
        am_recyclerview.layoutManager = LinearLayoutManager(this)
        am_recyclerview.adapter = mAdapter

        initData()
    }

    private fun initData() {
        val dataList: ArrayList<MainBean> = ArrayList()

        var mainBean: MainBean
        for (i in 1..10) {
            mainBean = MainBean("24K纯帅" + i, R.mipmap.ic_launcher_round)
            dataList.add(mainBean)
        }
        mAdapter.setDataList(dataList)
    }
}