package com.xiaolu.mylibrarykotlin.base

import android.os.Bundle
import com.xiaolu.mylibrarykotlin.mvpbase.IBasePresenter
import com.xiaolu.mylibrarykotlin.mvpbase.IPresenter
import com.xiaolu.mylibrarykotlin.mvpbase.IView
import com.xiaolu.mylibrarykotlin.mvpbase.MvpCallback
import com.xiaolu.mylibrarykotlin.net.RxManager

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.base
 * @ClassName: BaseMvpActivity
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/10/28 13:40
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/10/28 13:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
abstract class BaseMvpActivity<V : IView?, P : IPresenter<V?>?> : RxBaseActivity(), MvpCallback<V?, P?> {
    override var presenter: P? = null
    override var mvpView: V? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpView = createView()
        if (presenter == null) {
            presenter = createPresenter()
            try {
                presenter!!.attachView(this as V)
                initDate()
                initDate("")
            } catch (e: Exception) {
                ClassCastException(this.toString() + "实现IPresenterView或者IPresenterView子类接口")
            }
        }
    }

    override fun onDestroy() {
        RxManager.instance?.clear(IBasePresenter::class.simpleName)
        if (isFinishing) {
            mvpView = null
            presenter = null
        }
        super.onDestroy()
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