package com.xiaolu.mylibrary.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BaseViewHolder {
    private int mPosition;
    /**
     * 用于存储holder里面的各个view，此集合比map效率高,但key必须为Integer
     */
    private SparseArray<View> mViews;
    /**
     * 复用的view
     */
    private View convertView;

    private BaseViewHolder(Context context, int position, int layoutId, ViewGroup parent) {
        this.mPosition = position;
        mViews = new SparseArray<>();
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        convertView.setTag(this);
    }

    public static BaseViewHolder getInstance(Context context, int layoutId, int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            return new BaseViewHolder(context, position, layoutId, parent);
        } else {
            BaseViewHolder holder = (BaseViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }

    }

    /**
     * 通过resourceId获取item里面的view
     *
     * @param resourceId 控件的id
     * @return (T) view
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int resourceId) {
        View view = mViews.get(resourceId);
        if (view == null) {
            view = convertView.findViewById(resourceId);
            mViews.put(resourceId, view);
        }
        return (T) view;
    }

    /**
     * 为textview类型填充内容
     *
     * @param resourceId 控件ID
     * @param text       textView内容
     * @return BaseViewHolder
     */
    public BaseViewHolder setText(int resourceId, CharSequence text) {
        ((TextView) getView(resourceId)).setText(text);
        return this;
    }

    /**
     * 为textView类型填充内容
     *
     * @param resourceId 控件ID
     * @param resid      String文件下的字符串
     * @return BaseViewHolder
     */
    public BaseViewHolder setText(int resourceId, int resid) {
        ((TextView) getView(resourceId)).setText(resid);
        return this;
    }

    /**
     * 为imageView类型填充内容
     *
     * @param context    上下文
     * @param resourceId 控件ID
     * @param url        图片地址
     * @return BaseViewHolder
     */
    public BaseViewHolder setImageView(Context context, int resourceId, String url) {
        /*Picasso.with(context)
                .load(url)
                .into(((ImageView) getView(resourceId)));*/
        return this;
    }

    /**
     * @param context    上下文
     * @param resourceId 控件ID
     * @param url        图片资源
     * @return BaseViewHolder
     */
    public BaseViewHolder setImageView(Context context, int resourceId, int url) {
       /* Picasso.with(context)
                .load(url)
                .into(((ImageView) getView(resourceId)));*/
        return this;
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
    public BaseViewHolder setImageView(Context context, int resourceId, String url, int error) {
//        Picasso.with(context)
//                .load(url)
//                .error(error)
//                .into(((ImageView) getView(resourceId)));
        return this;
    }

    public View getConvertView() {
        return convertView;
    }

    /**
     * 获取当前item的位置
     *
     * @return mPosition
     */
    public int getPosition() {
        return mPosition;
    }
}
