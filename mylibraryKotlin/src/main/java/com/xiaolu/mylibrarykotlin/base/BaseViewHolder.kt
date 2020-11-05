package com.xiaolu.mylibrarykotlin.base

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class BaseViewHolder private constructor(context: Context, var position: Int, layoutId: Int, parent: ViewGroup) {
    /**
     * 获取当前item的位置
     *
     * @return mPosition
     */
    /**
     * 用于存储holder里面的各个view，此集合比map效率高,但key必须为Integer
     */
    private val mViews: SparseArray<View?>

    /**
     * 复用的view
     */
    val convertView: View

    /**
     * 通过resourceId获取item里面的view
     *
     * @param resourceId 控件的id
     * @return (T) view
     */
    fun <T : View?> getView(resourceId: Int): T? {
        var view = mViews[resourceId]
        if (view == null) {
            view = convertView.findViewById(resourceId)
            mViews.put(resourceId, view)
        }
        return view as T?
    }

    /**
     * 为textview类型填充内容
     *
     * @param resourceId 控件ID
     * @param text       textView内容
     * @return BaseViewHolder
     */
    fun setText(resourceId: Int, text: CharSequence?): BaseViewHolder {
        (getView<View>(resourceId) as TextView).text = text
        return this
    }

    /**
     * 为textView类型填充内容
     *
     * @param resourceId 控件ID
     * @param resid      String文件下的字符串
     * @return BaseViewHolder
     */
    fun setText(resourceId: Int, resid: Int): BaseViewHolder {
        (getView<View>(resourceId) as TextView).setText(resid)
        return this
    }

    /**
     * 为imageView类型填充内容
     *
     * @param context    上下文
     * @param resourceId 控件ID
     * @param url        图片地址
     * @return BaseViewHolder
     */
    fun setImageView(context: Context?, resourceId: Int, url: String?): BaseViewHolder {
        /*Picasso.with(context)
                .load(url)
                .into(((ImageView) getView(resourceId)));*/
        return this
    }

    /**
     * @param context    上下文
     * @param resourceId 控件ID
     * @param url        图片资源
     * @return BaseViewHolder
     */
    fun setImageView(context: Context?, resourceId: Int, url: Int): BaseViewHolder {
        /* Picasso.with(context)
                .load(url)
                .into(((ImageView) getView(resourceId)));*/
        return this
    }

    /**
     * 为imageView类型填充内容
     *
     * @param context    上下文
     * @param resourceId 控件ID
     * @param url        图片地址
     * @param error      错误图片
     * @return BaseViewHolder
     */
    fun setImageView(context: Context?, resourceId: Int, url: String?, error: Int): BaseViewHolder {
//        Picasso.with(context)
//                .load(url)
//                .error(error)
//                .into(((ImageView) getView(resourceId)));
        return this
    }

    companion object {
        fun getInstance(context: Context, layoutId: Int, position: Int, convertView: View?, parent: ViewGroup): BaseViewHolder {
            return if (convertView == null) {
                BaseViewHolder(context, position, layoutId, parent)
            } else {
                val holder = convertView.tag as BaseViewHolder
                holder.position = position
                holder
            }
        }
    }

    init {
        mViews = SparseArray()
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false)
        convertView.tag = this
    }
}