package com.xiaolu.mylibrarykotlin.base

import android.os.Bundle
import com.xiaolu.mylibrarykotlin.mvpbase.BaseContract
import com.xiaolu.mylibrarykotlin.mvpbase.BasePresenter

@Deprecated("")
abstract class LazyFragment<P : BasePresenter<V>?, V : BaseContract.BaseView?> : BaseLazyFragment() {
    protected var presenter: P? = null

    //实例化Presenter
    abstract fun initPresenter(): P
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = initPresenter()
        try {
            presenter!!.attachView(this as V)
            //checkActivityAttached();
            // initDate();
        } catch (e: Exception) {
            ClassCastException(this.toString() + "实现BasePresenter或者BaseView子类接口")
        }
    }

    override fun onDestroy() {
        if (presenter != null) {
            presenter?.detachView()
        }
        super.onDestroy()
    }

    override fun onResume() {
        if (presenter != null) {
            presenter?.attachView(this as V)
        } else {
            presenter = initPresenter()
        }
        super.onResume()
    }
}