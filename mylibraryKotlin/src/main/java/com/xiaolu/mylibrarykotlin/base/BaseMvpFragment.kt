package com.xiaolu.mylibrarykotlin.base

import android.os.Bundle
import com.xiaolu.mylibrarykotlin.mvpbase.IPresenter
import com.xiaolu.mylibrarykotlin.mvpbase.IView
import com.xiaolu.mylibrarykotlin.mvpbase.MvpCallback
import com.xiaolu.mylibrarykotlin.net.RxManager

/**
 * @author: zhaol
 * @createdOn: 2018/5/17 16:19
 * @description: $desc$
 */
abstract class BaseMvpFragment<V : IView?, P : IPresenter<V?>?> : RXBaseFragment(), MvpCallback<V?, P?> {
    override var presenter: P? = null
    override var mvpView: V? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mvpView = createView()
        if (presenter == null) {
            presenter = createPresenter()
            try {
                presenter!!.attachView(this as V)
                initDate()
            } catch (e: Exception) {
                ClassCastException(this.toString() + "实现BasePresenter或者BaseView子类接口")
            }
        }
    }

    override fun onDestroy() {
        RxManager.instance?.clear(BaseMvpActivity::class.simpleName)
        if (presenter != null) {
            mvpView = null
            presenter = null
        }
        super.onDestroy()
    }

    override fun onDestroyView() {
        if (presenter != null) {
            mvpView = null
            presenter = null
        }
        super.onDestroyView()
    }

    override fun onResume() {
        if (presenter != null) {
            presenter?.attachView(this as V)
        } else {
            presenter = createPresenter()
        }
        super.onResume()
    }
}