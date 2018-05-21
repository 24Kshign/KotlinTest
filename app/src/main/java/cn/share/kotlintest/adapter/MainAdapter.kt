package cn.share.kotlintest.adapter

import android.content.Context
import cn.share.kotlintest.R
import cn.share.kotlintest.bean.MainBean
import cn.share.kotlintest.recyclerview.BaseCommonAdapter
import cn.share.kotlintest.recyclerview.BaseCommonViewHolder
import kotlinx.android.synthetic.main.item_main.view.*

/**
 * Created by jack on 2018/5/21
 */
class MainAdapter(context: Context) : BaseCommonAdapter<MainBean>(context) {

    override fun getLayoutRes(): Int {
        return R.layout.item_main
    }

    override fun convert(holder: BaseCommonViewHolder, data: MainBean?, position: Int, payloads: List<Any>?) {
        if (null != data) {
            holder.setImageResource(holder.itemView.im_iv_avatar, data.image_res)
            holder.setText(holder.itemView.im_tv_title, data.title)
        }
    }
}