package com.xiaolu.mylibrary.mvpbase;

import androidx.annotation.CallSuper;

import com.xiaolu.mylibrary.load.callback.BaseCallBack;
import com.xiaolu.mylibrary.load.core.Load;
import com.xiaolu.mylibrary.load.core.LoadService;

/**
 * @author: zhaol
 * @createdOn: 2018/5/16 9:26
 * @description: MVP中presenter的父类
 */
public abstract class IBasePresenter<V extends IView, M extends IModel> {
    protected V baseView;
    protected LoadService register;
    protected M mModel;
    /**
     * 绑定的view
     */
    /**
     * 绑定view，一般在初始化中调用该方法
     */
    @CallSuper
    public void attachView(V mvpView) {
        this.baseView = mvpView;
        if (mModel == null) {
            mModel = createModel();
        }
    }

    /**
     * 初始化model
     *
     * @return
     */
    protected abstract M createModel();

    /**
     * 断开view，一般在onDestroy中调用
     */
    @CallSuper
    public void detachView() {
        this.baseView = null;
        this.register = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return baseView != null;
    }

    public M getModel() {
        return mModel;
    }

    public void setLoadService(Object object, BaseCallBack.OnReloadListener onReloadListener) {
        register = Load.getDefault().register(object, onReloadListener);
    }

    public LoadService getLoadService() {
        return register;
    }
}
