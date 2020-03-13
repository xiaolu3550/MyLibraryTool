package com.xiaolu.mylibrary.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    public Context mContext;
    private List<T> mDatas;

    public MyBaseAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder = BaseViewHolder.getInstance(mContext, bindView(), position, convertView, parent);
        convert(holder, mDatas.get(position));
        return holder.getConvertView();
    }

    /**
     * 填充holder里面控件的数据
     *
     * @param holder viewHolder
     * @param bean   Bean类
     */
    public abstract void convert(BaseViewHolder holder, T bean);

    /**
     * 绑定视图
     *
     * @return
     */
    public abstract int bindView();
}
