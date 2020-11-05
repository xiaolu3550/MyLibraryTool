package com.xiaolu.mylibrarykotlin.mvpbase

import androidx.lifecycle.LifecycleOwner

/**
 * ================================================
 *
 * @ProjectName: MyLibraryTool
 * @Package: com.xiaolu.mylibrary.mvpbase
 * @ClassName: MvpCallback
 * @Description: java类作用描述
 * @Author: xiaol
 * @CreateDate: 2020/10/28 13:46
 * @UpdateUser: xiaol
 * @UpdateDate: 2020/10/28 13:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * ================================================
 */
interface MvpCallback<V, P : IPresenter<V>?> where V : IView?, V : LifecycleOwner? {
    /**
     * 创建Presenter  调用在onCreate中
     *
     * @return
     */
    fun createPresenter(): P
    //创建View
    /**
     * 创建View
     *
     * @return
     */
    fun createView(): V

    /**
     * 获取Presenter
     *
     * @return
     */
    /**
     * 设置Presenter
     *
     * @param presenter
     */
    var presenter: P

    /**
     * 获取View
     *
     * @return
     */
    /**
     * 设置View
     *
     * @param view
     */
    var mvpView: V

}