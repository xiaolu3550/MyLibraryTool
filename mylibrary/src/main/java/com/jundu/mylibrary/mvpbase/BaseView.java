package com.jundu.mylibrary.mvpbase;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author: zhaol
 * @createdOn: 2018/5/16 9:26
 * @description: MVP中View的父类
 */
public interface BaseView {
    /**
     * 显示请求错误提示
     *
     * @param message 错误信息
     */
    void onError(String message);

    /**
     * 显示请求加载提示
     */
    void onLoad();

    /**
     * 加载成功
     */
    void onDisMiss();

    /**
     * 加载数据为null时
     */
    void onEmpty();

    /**
     * 获取上下文
     *
     * @return context
     */
    Context getContext();

    /**
     * 绑定RXLifecycle生命周期
     *
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLifecycles();
}
