package com.xiaolu.mylibrarykotlin.mvpbase

import com.xiaolu.mylibrarykotlin.load.callback.BaseCallBack
import com.xiaolu.mylibrarykotlin.load.core.Load
import com.xiaolu.mylibrarykotlin.load.core.LoadService

/**
 * @author: zhaol
 * @createdOn: 2018/5/16 9:26
 * @description: MVP中presenter的父类
 */
class BasePresenter<V : BaseContract.BaseView?> {
    /**
     * 获取连接的view
     */
    var view: V? = null
        protected set
    protected var register: LoadService<Any>? = null
    /**
     * 绑定的view
     */
    /**
     * 绑定view，一般在初始化中调用该方法
     */
    fun attachView(mvpView: V) {
        view = mvpView
    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    fun detachView() {
        view = null
        register = null
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    val isViewAttached: Boolean
        get() = view != null

    fun setLoadService(any: Any?, onReloadListener: BaseCallBack.OnReloadListener?) {
        register = Load.default?.register(any, onReloadListener)
    }

    val loadService: LoadService<Any>?
        get() = register
}