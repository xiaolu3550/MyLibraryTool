package com.xiaolu.mylibrary.mvpbase;

/**
 * @author: zhaol
 * @createdOn: 2018/5/16 9:26
 * @description: MVP中presenter的父类
 */
public class BasePresenter<V extends BaseView> {
    protected V baseView;
    /**
     * 绑定的view
     */
    /**
     * 绑定view，一般在初始化中调用该方法
     */
    public void attachView(V mvpView) {
        this.baseView = mvpView;
    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    public void detachView() {
        this.baseView = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return baseView != null;
    }

    /**
     * 获取连接的view
     */
    public V getView() {
        return baseView;
    }
}
