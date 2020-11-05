package com.xiaolu.mylibrarykotlin.mvpbase

import androidx.annotation.CallSuper

import com.xiaolu.mylibrarykotlin.load.callback.BaseCallBack
import com.xiaolu.mylibrarykotlin.load.core.Load
import com.xiaolu.mylibrarykotlin.load.core.LoadService

/**
 * @author: zhaol
 * @createdOn: 2018/5/16 9:26
 * @description: MVP中presenter的父类
 */
abstract class IBasePresenter<V : IView?, M : IModel?> {
    /**
     * 获取连接的view
     */
    var view: V? = null
        protected set
    protected var register: LoadService<Any>? = null

    /**
     * 获取model
     *
     * @return
     */
    var model: M? = null
        protected set

    /**
     * 绑定view，一般在初始化中调用该方法
     */
    @CallSuper
    fun attachView(mvpView: V) {
        view = mvpView
        if (model == null) {
            model = createModel()
        }
    }

    /**
     * 初始化model
     *
     * @return
     */
    protected abstract fun createModel(): M

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

    /**
     * 断开view，一般在onDestroy中调用
     */
    @CallSuper
    fun detachView() {
        view = null
        register = null
        model = null
    }
}