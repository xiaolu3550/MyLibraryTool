package com.xiaolu.mylibrarykotlin.base

import android.os.Bundle
import com.xiaolu.mylibrarykotlin.mvpbase.BaseContract
import com.xiaolu.mylibrarykotlin.mvpbase.BasePresenter

/**
 * ================================================
 *
 * @ProjectName: mylibrary
 * @Package: com.xiaolu.mylibrary.base
 * @ClassName: BaseActivity
 * @Description: Activity父类
 * @Author: 赵璐
 * @CreateDate: 2020/7/13 13:49
 * @UpdateUser: 更新者: 赵璐
 * @UpdateDate: 2020/7/13 13:49
 * @UpdateRemark: 更新内容:
 * @Version: 1.0
 * ================================================
 */
abstract class BaseActivity<P : BasePresenter<V>?, V : BaseContract.BaseView?> : RxBaseActivity() {
    protected var presenter: P? = null

    /**
     * 初始Presenter
     *
     * @return P
     * @method initPresenter
     * @description: 初始Presenter
     * @date: 2020/7/13 13:51
     * @author: 赵璐
     */
    abstract fun initPresenter(): P
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = initPresenter()
        try {
            presenter!!.attachView(this as V)
            initDate()
            initDate("")
        } catch (e: Exception) {
            ClassCastException(this.toString() + "实现IPresenterView或者IPresenterView子类接口")
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