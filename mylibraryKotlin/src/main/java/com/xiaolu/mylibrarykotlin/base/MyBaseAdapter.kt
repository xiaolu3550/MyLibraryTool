package com.xiaolu.mylibrarykotlin.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

abstract class MyBaseAdapter<T>(var mContext: Context, private val mDatas: List<T>?) : BaseAdapter() {
    override fun getCount(): Int {
        return mDatas?.size ?: 0
    }

    override fun getItem(position: Int): T {
        return mDatas!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        val holder = BaseViewHolder.getInstance(mContext, bindView(), position, convertView, parent)
        convert(holder, mDatas!![position])
        return holder.convertView
    }

    /**
     * 填充holder里面控件的数据
     *
     * @param holder viewHolder
     * @param bean   Bean类
     */
    abstract fun convert(holder: BaseViewHolder?, bean: T)

    /**
     * 绑定视图
     *
     * @return
     */
    abstract fun bindView(): Int

}